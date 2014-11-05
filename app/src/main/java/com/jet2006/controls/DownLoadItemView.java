package com.jet2006.controls;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.jet2006.entity.DownLoadData;
import com.jet2006.enumdefin.DownLoadEnum;
import com.jet2006.terry.jetlearning.R;

public class DownLoadItemView extends FrameLayout {

    private DownLoadData downLoadData;
    private String localFolder;
    private String localFilePath;
    private final String baseUrl = "http://192.168.1.66:8588/";

    private final View layoutView;
    private final TextView nameView;
    private final TextView infoView;
    private final ProgressBar progressBar;
    private final Context mContext;

    public DownLoadItemView(Context context, DownLoadData data) {
        super(context);

        this.mContext = context;

        this.downLoadData = data;

        this.layoutView = LayoutInflater.from(context).inflate(
                R.layout.download_list_item, this);

        this.nameView = (TextView) layoutView.findViewById(R.id.name);
        this.infoView = (TextView) layoutView.findViewById(R.id.info);
        this.progressBar = (ProgressBar) layoutView.findViewById(R.id.progress);

    }

    public void Start() {
        if (null != downLoadData) {
            if (downLoadData.getStatus() == DownLoadEnum.Start) {
                return;
            }
            new DownLoadTask().execute();
        }
    }

    public void Pause() {
        this.downLoadData.setStatus(DownLoadEnum.Pause);
    }

    private void updateView() {
        this.nameView.setText(this.downLoadData.getName());
        this.infoView.setText(this.downLoadData.getInfo());

        this.progressBar.setMax(this.downLoadData.getFilesize());
        this.progressBar.setProgress(this.downLoadData.getCompletesize());
    }

    public class DownLoadTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            try {
                return getFileInfo();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";
        }

        @Override
        protected void onPostExecute(String result) {

            int size = Integer.parseInt(result);
            downLoadData.setFilesize(size);

            if (createFile()) {
                new DownLoadThread(downLoadData).start();
            }
        }
    }

    public class updateViewTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            updateView();
        }
    }

    @SuppressLint("NewApi")
    private Boolean createFile() {
        try {
            File[] files = mContext.getExternalFilesDirs("");
            String path = files[1].getPath();

            // 设置存储的目录
            this.localFolder = path + "/JetFolder/";

            File folder = new File(this.localFolder);
            if (!folder.exists()) {
                folder.mkdir();
            }

            // 设置文件存放的全路径，包含文件名称
            this.localFilePath = this.localFolder + this.downLoadData.getName();

            File testFile = new File(this.localFilePath);
            if (!testFile.exists()) {
                testFile.createNewFile();
            }

            return true;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    private String getFileInfo() throws IOException {

        HttpURLConnection connection = null;
        InputStream inStream = null;

        try {

            downLoadData.setStatus(DownLoadEnum.Start);

            URL url = new URL(baseUrl + "FileInfo.aspx");

            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setReadTimeout(10 * 1000);
            connection.setConnectTimeout(15 * 1000);

            connection.connect();

            OutputStream out = connection.getOutputStream();
            DataOutputStream dos = new DataOutputStream(out);
            dos.writeBytes("name="
                    + URLEncoder.encode(this.downLoadData.getName(), "utf-8"));
            dos.close();

            inStream = connection.getInputStream();
            InputStreamReader inReader = new InputStreamReader(inStream);
            BufferedReader bufferedReader = new BufferedReader(inReader);
            String result = null;
            StringBuffer sBuffer = new StringBuffer();
            while (null != (result = bufferedReader.readLine())) {
                sBuffer.append(result);
            }

            String info = sBuffer.toString();
            return info;

        } finally {
            if (inStream != null) {
                inStream.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    public class DownLoadThread extends Thread {

        private final DownLoadData mDownLoadData;

        public DownLoadThread(DownLoadData downLoadData) {
            this.mDownLoadData = downLoadData;
        }

        @SuppressWarnings("resource")
        @Override
        public void run() {

            HttpURLConnection connection = null;
            InputStream inputStream = null;
            RandomAccessFile accessFile = null;

            try {

                int completeSize = this.mDownLoadData.getCompletesize();

                URL url = new URL(baseUrl
                        + "default.aspx?name="
                        + URLEncoder.encode(this.mDownLoadData.getName(),
                        "utf-8"));

                connection = (HttpURLConnection) url.openConnection();

                connection.setRequestMethod("GET");
                connection.setConnectTimeout(15 * 1000);

                // Rang格式,bytes= start - end
                connection.setRequestProperty("Range",
                        "bytes=" + Integer.toString(completeSize) + "-");
                connection.connect();

                accessFile = new RandomAccessFile(localFilePath, "rwd");
                accessFile.seek(completeSize);

                inputStream = connection.getInputStream();
                byte[] buffer = new byte[4096];
                int lenght = -1;

                while ((lenght = inputStream.read(buffer)) != -1) {

                    accessFile.write(buffer, 0, lenght);
                    completeSize += lenght;

                    this.mDownLoadData.setCompletesize(completeSize);

                    new updateViewTask().execute();

                    if (this.mDownLoadData.getStatus() == DownLoadEnum.Pause) {
                        return;
                    }
                }

                this.mDownLoadData.setStatus(DownLoadEnum.Complete);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {

                try {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (accessFile != null) {
                        accessFile.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (connection != null) {
                    connection.disconnect();
                }
            }
        }
    }
}
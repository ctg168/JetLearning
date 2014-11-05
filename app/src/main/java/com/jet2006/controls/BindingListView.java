package com.jet2006.controls;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jet2006.converter.IValueConverter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("UseSparseArrays")
public class BindingListView extends PullToRefreshListView {

    private ArrayList<Map<String, Object>> mData = new ArrayList<Map<String, Object>>();
    private Map<Integer, IValueConverter> mConverter = new HashMap<Integer, IValueConverter>();
    private Map<Integer, OnClickListener> mOnClickListener = new HashMap<Integer, View.OnClickListener>();
    private int mResource;
    private String[] mDataStruct;
    private BindingAdapter adapter;

    public BindingListView(Context context) {
        super(context);
    }

    public BindingListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setItemLayout(int resource, String[] dataStruct) {
        this.mResource = resource;
        this.mDataStruct = dataStruct;
        this.adapter = new BindingAdapter(getContext(), mData, mResource,
                mDataStruct);

        this.adapter.setItemConverter(mConverter);
        this.adapter.setOnClickListener(mOnClickListener);
        this.setAdapter(this.adapter);
    }

    public void setDataSource(List<? extends Object> javaBeanList) {

        this.mData.clear();
        this.addAllData(javaBeanList);
    }

    public void addData(Object javaBean) {
        if (this.mData != null) {
            this.mData.add(toMap(javaBean));
        }
        this.adapter.notifyDataSetChanged();
    }

    public void addAllData(List<? extends Object> javaBeanList) {
        if (this.mData != null && javaBeanList != null) {
            for (Object obj : javaBeanList) {
                this.mData.add(toMap(obj));
            }
        }

        this.adapter.notifyDataSetChanged();
    }

    public void addValueConverter(int resource, IValueConverter converter) {
        this.mConverter.put(resource, converter);
    }

    public void setOnClickListener(int resource,
                                   View.OnClickListener clickListener) {
        this.mOnClickListener.put(resource, clickListener);
    }

    @SuppressLint("DefaultLocale")
    private Map<String, Object> toMap(Object javaBean) {
        Map<String, Object> result = new HashMap<String, Object>();

        Method[] methods = javaBean.getClass().getDeclaredMethods();

        for (Method method : methods) {
            try {
                if (method.getName().startsWith("get")) {
                    String field = method.getName();
                    field = field.substring(field.indexOf("get") + 3);

                    //�˴�ӦΪʹ�õ�.NET����.NET��Entity��ʽ��JavaBean�ĸ�ʽ���뱣��һ��
                    //���д˴��������ڱ�׼��JavaBean��ʽ
                    //field = field.toLowerCase().charAt(0) + field.substring(1);

                    Object value = method.invoke(javaBean, (Object[]) null);
                    result.put(field, null == value ? "" : value.toString());
                }

            } catch (Exception e) {

            }
        }
        return result;
    }

    public class BindingAdapter extends BaseAdapter {
        private Context mContext;
        private int mResource;
        private LayoutInflater mInflater = null;
        private List<? extends Map<String, ?>> mData;
        private Map<Integer, IValueConverter> mConverter;
        private Map<Integer, View.OnClickListener> mOnClickListener;
        private String[] mDataStruct;
        private final String packageName;

        public BindingAdapter(Context context,
                              List<? extends Map<String, ?>> data, int resource,
                              String[] dataStruct) {
            this.mContext = context;
            this.mData = data;
            this.mResource = resource;
            this.mDataStruct = dataStruct;

            this.mInflater = LayoutInflater.from(context);

            this.packageName = this.mContext.getPackageName();
        }

        public void setItemConverter(Map<Integer, IValueConverter> converter) {
            this.mConverter = converter;
        }

        public void setOnClickListener(
                Map<Integer, View.OnClickListener> onClickListener) {
            this.mOnClickListener = onClickListener;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Log.v("getview",
                    "--------------------------" + Integer.toString(position));
            return createViewFromResource(position, convertView, parent,
                    mResource);
        }

        private View createViewFromResource(int position, View convertView,
                                            ViewGroup parent, int resource) {
            View v;
            if (convertView == null) {
                v = mInflater.inflate(mResource, parent, false);
            } else {
                v = convertView;
            }

            bindView(position, v);

            return v;
        }

        @SuppressWarnings("rawtypes")
        private void bindView(int position, View view) {
            final Map dataSet = mData.get(position);
            if (null == dataSet) {
                return;
            }

            final String[] dataStruct = mDataStruct;
            final int count = dataStruct.length;

            for (int i = 0; i < count; i++) {
                int vId = getId(dataStruct[i], view);
                final View v = view.findViewById(vId);

                if (null != v) {
                    final Object data = dataSet.get(dataStruct[i]);

                    // ʹ��View���Զ�����ִ���ʽ
                    if (null != mConverter) {
                        IValueConverter converter = this.mConverter.get(vId);
                        if (null != converter) {
                            converter.Convert(v, data, dataSet);
                            continue;
                        }
                    }

                    String text = null == data ? "" : data.toString();
                    if (null == text) {
                        text = "";
                    }

                    if (v instanceof Checkable) {
                        if (data instanceof Boolean) {
                            ((Checkable) v).setChecked((Boolean) data);
                        } else if (v instanceof TextView) {
                            setViewText((TextView) v, text);
                        } else {
                            throw new IllegalStateException(v.getClass()
                                    .getName()
                                    + " �󶨽�֧��Boolean,������ "
                                    + (data == null ? "<null>"
                                    : data.getClass()));
                        }

                    } else if (v instanceof TextView) {
                        setViewText((TextView) v, text);
                    } else if (v instanceof ImageView) {
                        if (data instanceof Integer) {
                            setViewImage((ImageView) v, (Integer) data);
                        } else {
                            setViewImage((ImageView) v, text);
                        }
                    } else {
                        throw new IllegalStateException(v.getClass().getName()
                                + "����ͼ����֧��");
                    }
                }
            }

            // ע����ͼ��Click�¼�
            if (null != mOnClickListener) {
                for (int resource : mOnClickListener.keySet()) {
                    View clickView = view.findViewById(resource);
                    if (null != clickView) {
                        clickView.setOnClickListener(mOnClickListener
                                .get(resource));
                    }
                }
            }
        }

        public void setViewImage(ImageView v, int value) {
            v.setImageResource(value);
        }

        public void setViewImage(ImageView v, String value) {
            try {
                v.setImageResource(Integer.parseInt(value));
            } catch (NumberFormatException nfe) {
                v.setImageURI(Uri.parse(value));
            }
        }

        private void setViewText(TextView v, String text) {
            v.setText(text);
        }

        private int getId(String name, View v) {
            int getId = v.getResources().getIdentifier(name, "id",
                    this.packageName);
            return getId;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
    }

}

package ExView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.com.testdata.R;
import com.example.com.testdata.TestData;

import java.util.List;

/**
 * Created by GXJ on 2016/4/12.
 */
public class GridAdapter extends BaseAdapter {
    private List<TestData.DataBean> listson;
    private Context context;

    public GridAdapter(Context context, List<TestData.DataBean> listson) {
        super();
        this.listson = listson;
        this.context = context;
    }

    @Override
    public int getCount() {
        return 9;
    }

    @Override
    public Object getItem(int position) {
        return listson.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder2 mViewHolder2;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.gridviewdetail, null);
            mViewHolder2 = new ViewHolder2();
            mViewHolder2.tx = (TextView) convertView.findViewById(R.id.tv);
            convertView.setTag(mViewHolder2);
        } else {
            mViewHolder2 = (ViewHolder2) convertView.getTag();
        }

        mViewHolder2.tx .setText(listson.get(position).getName());
        if(position==getCount()-1){


//            TextView tv = new TextView(context);
//            tv.setText("更多");
//            tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                    ViewGroup.LayoutParams.MATCH_PARENT));

//            tv.setPadding(20,40,20,40);
//            tv.setGravity(Gravity.CENTER);
//            return tv;
            mViewHolder2.tx.setText("更多");
        }
        return convertView;
    }

    class ViewHolder2 {
        TextView tx;
    }
}

package ExView;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.com.testdata.R;
import com.example.com.testdata.TestData;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by GXJ on 2016/4/12.
 */
public class NewExpandableListAdapter extends BaseExpandableListAdapter{

    private Context context;
    private List<TestData.DataBean> father = new ArrayList<>();
    private Map<String,List<TestData.DataBean>> son = new HashMap<>();

    public NewExpandableListAdapter(Context context, List<TestData.DataBean> father, Map<String,List<TestData.DataBean>> son) {
        super();
        this.context = context;
        this.father = father;
        this.son = son;
    }

    public void setAllData(Collection<? extends TestData.DataBean> father,Map<String,List<TestData.DataBean>> son){
        this.father.addAll(father);
        this.son.putAll(son);
        notifyDataSetChanged();
    }

    //得到子item需要关联的数据
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        String key = father.get(groupPosition).getName();
        return (son.get(key).get(childPosition));
    }

    //得到子item的ID
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    //设置子item的组件
    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        String key = father.get(groupPosition).getName();
        String info = son.get(key).get(childPosition).getName();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.exgridview, null);
        }
        MyGridView tv = (MyGridView) convertView
                .findViewById(R.id.gridview);
        GridAdapter adapter = new GridAdapter(context, son.get(key));
        tv.setAdapter(adapter);

        return tv;
    }

    //获取当前父item下的子item的个数
    @Override
    public int getChildrenCount(int groupPosition) {
//        String key = father.get(groupPosition);
//        int size=son.get(key).size();
        return 1;
    }
    //获取当前父item的数据
    @Override
    public Object getGroup(int groupPosition) {
        return father.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return father.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
    //设置父item组件
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.layout_parent, null);
        }
        TextView tv = (TextView) convertView
                .findViewById(R.id.parent_textview);
        SimpleDraweeView sv = (SimpleDraweeView) convertView.findViewById(R.id.iv_pet);
        ImageView iv_shangxia = (ImageView)convertView.findViewById(R.id.iv_shangxia);
        sv.setImageURI(Uri.parse(father.get(groupPosition).getPicSmall()));
        tv.setText(father.get(groupPosition).getName());
        iv_shangxia.setBackgroundResource(R.mipmap.xiangshang);
        if(!isExpanded){
            iv_shangxia.setBackgroundResource(R.mipmap.xiangxia);//或者imageView.setImageResource(R.drawable.mm_submenu_down);
        }
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}






package beauty.android.com.paradisebeaity.Adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import beauty.android.com.paradisebeaity.Models.DummyModel;
import beauty.android.com.paradisebeaity.R;
import beauty.android.com.paradisebeaity.ui.AnimatedExpandableListView;

/**
 * Created by Hassan on 14/01/2017.
 */

public class ExampleAdapter extends AnimatedExpandableListView.AnimatedExpandableListAdapter {
    private LayoutInflater inflater;

    private List<GroupItem> items;

    public ExampleAdapter(Context context) {
        inflater = LayoutInflater.from(context);

    }

    public void setData(List<GroupItem> items) {
        this.items = items;
    }

    @Override
    public ChildItem getChild(int groupPosition, int childPosition) {
        return items.get(groupPosition).items.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getRealChildView(int groupPosition, int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder holder;
        ChildItem item = getChild(groupPosition, childPosition);
        if (convertView == null) {
            holder = new ChildHolder();
            convertView = inflater.inflate(R.layout.drawer_sous_item, parent,
                    false);
            holder.icon = (ImageView) convertView
                    .findViewById(R.id.Drawer_sous_image);
            holder.title = (TextView) convertView.findViewById(R.id.Drawer_sous_title);

				/*holder.hint = (TextView) convertView
						.findViewById(R.id.textHint);*/
            convertView.setTag(holder);
        } else {
            holder = (ChildHolder) convertView.getTag();
        }

        holder.title.setText(item.title);
        holder.icon.setImageResource(item.image);
        //holder.hint.setText(item.hint);

        return convertView;
    }

    @Override
    public int getRealChildrenCount(int groupPosition) {
        return items.get(groupPosition).items.size();
    }

    @Override
    public GroupItem getGroup(int groupPosition) {
        return items.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return items.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        GroupHolder holder;
        GroupItem item = getGroup(groupPosition);

        if (convertView == null) {
            holder = new GroupHolder();
            convertView = inflater.inflate(R.layout.list_view_item_navigation_drawer_social, parent,
                    false);

            holder.icon = (ImageView) convertView
                    .findViewById(R.id.Icone_drawer);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.Desc = (TextView) convertView.findViewById(R.id.ListDesc);
            convertView.setTag(holder);
        } else {
            holder = (GroupHolder) convertView.getTag();
        }

        holder.title.setText(item.title);
        holder.icon.setImageResource(item.image);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int arg0, int arg1) {
        return true;
    }

    public static class GroupItem {
        String desc;
        int image;
        String title;
        List<ChildItem> items = new ArrayList<ChildItem>();

        public GroupItem(String desc, int image, String title, List<ChildItem> items) {
            this.desc = desc;
            this.image = image;
            this.title = title;
            this.items = items;
        }
    }

    public static class ChildItem {

        int image;
        String title;
        //String hint;


        public ChildItem( int image, String title) {

            this.image = image;
            this.title = title;
        }
    }

    public static class ChildHolder {
        public ImageView icon;
        public/* Roboto */TextView title;

    }

    public static class GroupHolder {
        public ImageView icon;
        public/* Roboto */TextView title;
        public TextView Desc;
        public ImageView back;
        public View mv;
        public LinearLayout line;
    }

}
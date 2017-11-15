package com.example.ph.projeto_final_ed.helper;


    import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

    import com.example.ph.projeto_final_ed.R;

    import java.util.ArrayList;

    /**
     * Created by cnbuff410
     */
    public class TreeAdapterTeste extends BaseExpandableListAdapter {
        private Activity mActivity;
        private ArrayList<String> mGroupNames = new ArrayList<String>();
        private ArrayList<ArrayList<String>> mGroupItems = new ArrayList<ArrayList<String>>();
       // private ArrayList<ArrayList<ArrayList<String>>> mGroupItems = new ArrayList<>();
        public TreeAdapterTeste(Activity activity) {
            mActivity = activity;
            mGroupNames.add("Group");
            mGroupNames.add("Group");
            mGroupNames.add("grupo teste");

            ArrayList<String> items1 = new ArrayList<String>();
            items1.add("left");
            items1.add("right");

            ArrayList<String> items2 = new ArrayList<String>();
            items2.add("left");
            items2.add("right");

            ArrayList<String> items3 = new ArrayList<String>();
            items3.add("left");
            items3.add("right");

            mGroupItems.add(items1);
            mGroupItems.add(items2);
            mGroupItems.add(items3);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            //return mGroupItems.get(groupPosition).get(childPosition);
            return mGroupItems.get(groupPosition).get(childPosition);
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                                 View view, ViewGroup parent) {
            ChildViewHolder viewHolder;
            // General ListView optimization code.
            if (view == null) {
                view = mActivity.getLayoutInflater().inflate(R.layout.model_tree_child, null);
                viewHolder = new ChildViewHolder();
                viewHolder.device = (TextView) view.findViewById(R.id.item_tree_child);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ChildViewHolder) view.getTag();
            }

            //viewHolder.device.setText(mGroupItems.get(groupPosition).get(childPosition));
            viewHolder.device.setText(mGroupItems.get(groupPosition).get(childPosition));
            return view;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return mGroupItems.get(groupPosition).size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return mGroupNames.get(groupPosition);
        }

        @Override
        public int getGroupCount() {
            return mGroupNames.size();
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isLastChild, View view,
                                 ViewGroup parent) {

            GroupViewHolder viewHolder;
            if (view == null) {
                view = mActivity.getLayoutInflater().inflate(R.layout.model_tree_group, null);
                viewHolder = new GroupViewHolder();
                viewHolder.name = (TextView) view.findViewById(R.id.item_tree_group);
                view.setTag(viewHolder);
            } else {
                viewHolder = (GroupViewHolder) view.getTag();
            }

            viewHolder.name.setText(mGroupNames.get(groupPosition));

            return view;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

        static class GroupViewHolder {
            TextView name;
        }

        static class ChildViewHolder {
            TextView device;
        }

    }

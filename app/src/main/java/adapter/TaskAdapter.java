package adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.taskmanagesys.R;

import java.util.ArrayList;

import beans.Task;
import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * Created by zql on 2015-05-18.
 */
public class TaskAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private ViewHolder holder = null;
    private Context context;
    private ArrayList<Task> taskArrayList = null;
    public static final int word_task_picture = 0;
    public static final int test_task_picture = 1;
    public static final int class_task_picture = 2;
    public static final int i_and_e_weight = 0;
    public static final int i_and_ne_weight = 1;
    public static final int ni_and_e_weight = 2;
    public static final int ni_and_ne_weight = 3;
    public TaskAdapter(Context context, ArrayList<Task> taskArrayList) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
        this.taskArrayList = taskArrayList;
    }

    @Override
    public int getCount() {
        return taskArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {



        if (null == convertView) {
            convertView = mInflater.inflate(R.layout.item_task_list, null);
            holder = new ViewHolder();
            holder.messageHead = (ImageView) convertView.findViewById(R.id.message_head);
            holder.tvMessageDetail = (TextView) convertView.findViewById(R.id.tv_message_detail);
            holder.tvMessageTime = (TextView) convertView.findViewById(R.id.tv_message_time);
            holder.tvMessageTitle = (TextView) convertView.findViewById(R.id.tv_message_title);
            holder.message_linear = (LinearLayout) convertView.findViewById(R.id.message_linear);
            convertView.setTag(holder);
        }else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        Log.v("a", taskArrayList.get(position).getName());
        holder.tvMessageDetail.setText(taskArrayList.get(position).getDescribe());
        holder.tvMessageTitle.setText(taskArrayList.get(position).getName());
        switch (taskArrayList.get(position).getTaskweigh()) {
            case i_and_e_weight:
                holder.tvMessageTitle.setTextColor(Color.rgb(200, 20, 60));
                break;
            case i_and_ne_weight:
                holder.tvMessageTitle.setTextColor(Color.rgb(75, 0, 130));
                break;
            case ni_and_e_weight:
                holder.tvMessageTitle.setTextColor(Color.rgb(255, 255, 255));
                break;
            case ni_and_ne_weight:
                holder.tvMessageTitle.setTextColor(Color.rgb(0, 0, 0));
                break;
            default:
                break;
        }

        holder.tvMessageTime.setText(taskArrayList.get(position ).getYear() + "/"
                                                 + taskArrayList.get(position ).getMonth() + "/"
                                                 + taskArrayList.get(position ).getDay() + "/"
                                                 + taskArrayList.get(position ).getHour() + "/"
                                                 + taskArrayList.get(position ).getMinute());

        switch (taskArrayList.get(position ).getTaskType()) {
            case word_task_picture:
                holder.messageHead.setImageResource(R.drawable.word_task);
                break;
            case test_task_picture:
                holder.messageHead.setImageResource(R.drawable.test_task);
                break;
            case class_task_picture:
                holder.messageHead.setImageResource(R.drawable.class_task);
                break;
            default:
                break;
        }

        holder.message_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.message_linear.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });
            return convertView;
        }
        class ViewHolder {
            ImageView messageHead;
            TextView tvMessageTitle;
            TextView tvMessageTime;
            TextView tvMessageDetail;
            LinearLayout message_linear;
        }
    }

package ciit.com.abdullah.donateforlife2.pkgNgoProjects;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;

import ciit.com.abdullah.donateforlife2.R;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ViewHolder> {
    List<project> projects;
    Context ctx;
    public ProjectAdapter(Context ctx , List<project> projects){
        this.ctx=ctx;
        this.projects=projects;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.project_card_layout, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder v1, int i) {
        project p= projects.get(i);
        Glide.with(ctx)
                .load(p.getImage_id())
                .into(v1.project_img);
        v1.project_title.setText(p.getTitles());
        v1.project_desc.setText(p.getDetails());
    }

    @Override
    public int getItemCount() {
        return projects.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView project_img;
        private TextView project_desc,project_title;
        public ViewHolder(View projectView) {
            super(projectView);
            project_img = (ImageView)projectView.findViewById(R.id.project__image);
            project_title = (TextView)projectView.findViewById(R.id.project_title);
            project_desc = (TextView)projectView.findViewById(R.id.project_desc1);
        }
    }
}
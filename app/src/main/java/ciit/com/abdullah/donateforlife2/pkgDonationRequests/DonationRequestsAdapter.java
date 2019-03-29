package ciit.com.abdullah.donateforlife2.pkgDonationRequests;


import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import ciit.com.abdullah.donateforlife2.R;
import ciit.com.abdullah.donateforlife2.pkgNgoProjects.ProjectAdapter;
import ciit.com.abdullah.donateforlife2.pkgNgoProjects.project;

public class DonationRequestsAdapter extends RecyclerView.Adapter<DonationRequestsAdapter.ViewHolder> {
    List<Request> requests;
    Context ctx;
    public DonationRequestsAdapter(List<Request> requests , Context ctx){
        this.ctx=ctx;
        this.requests=requests;
    }
    @Override
    public DonationRequestsAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.request_card_layout, null);
        return new DonationRequestsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DonationRequestsAdapter.ViewHolder v1, int i) {
        Request p=requests.get(i);
        v1.req_title.setText(p.getTitles());
        v1.req_desc.setText(p.getDetails());
        Glide.with(ctx)
                .load(p.getImage_id())
                .into(v1.req_img);
    }

    @Override
    public int getItemCount() {
        return requests.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView req_img;
        private TextView req_desc,req_title;

        public ViewHolder(View requestView) {
            super(requestView);
            req_img = (ImageView)requestView.findViewById(R.id.request_image);
            req_title = (TextView)requestView.findViewById(R.id.request_title);
            req_desc = (TextView)requestView.findViewById(R.id.request_detail);
        }

    }


}
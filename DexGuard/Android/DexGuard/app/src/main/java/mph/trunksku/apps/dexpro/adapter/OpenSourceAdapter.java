package mph.trunksku.apps.dexpro.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.mph.dexprotect.R;
import java.util.ArrayList;
import java.util.HashMap;
import mph.trunksku.apps.dexpro.adapter.OpenSourceAdapter;

public class OpenSourceAdapter extends RecyclerView.Adapter<OpenSourceAdapter.MyViewHolder> {

    private Context mContext;
    ArrayList<HashMap<String, String>> data;
    

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView libname;

        private TextView libcreator;

        private TextView libdesc;

        private TextView libversion;

        private TextView liblicense;

        private CardView card;

        public MyViewHolder(View view) {
            super(view);
            card = (CardView) view.findViewById(R.id.openCard);
            libname = (TextView) view.findViewById(R.id.libraryName);
            libcreator = (TextView) view.findViewById(R.id.libraryCreator);
            libdesc = (TextView) view.findViewById(R.id.libraryDescription);
            libversion = (TextView) view.findViewById(R.id.libraryVersion);
            liblicense = (TextView) view.findViewById(R.id.libraryLicense);
        }
    }


    public OpenSourceAdapter(Context mContext, ArrayList<HashMap<String, String>> arraylist) {
        this.mContext = mContext;
        this.data = arraylist;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      
        View itemView = LayoutInflater.from(mContext)
            .inflate(R.layout.layout_list_item_opensource, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        //super.bindView(viewHolder, list);
        try
        {
            HashMap<String, String> resultp = data.get(position);
            /*if (Apps.getSharedPreferences().getInt("ServerPos", 0) == position) {
                holder.sll.setBackgroundColor(Color.parseColor("#585858"));
            } else {
                holder.sll.setBackgroundColor(Color.parseColor("#00000000"));
            }*/
            holder.libname.setText(resultp.get("Name"));
            holder.libcreator.setText(resultp.get("Creator"));
            holder.libdesc.setText(resultp.get("Desc"));
            holder.libversion.setText(resultp.get("Version"));
            holder.liblicense.setText(resultp.get("License"));
        } catch(Exception e) {
            Toast.makeText(mContext, e.getMessage(), 1).show();
        }
    }


    @Override
    public int getItemCount() {
        return data.size();
    }
    
}


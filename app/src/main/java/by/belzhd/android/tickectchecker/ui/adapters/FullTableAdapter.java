package by.belzhd.android.tickectchecker.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import by.belzhd.android.tickectchecker.R;
import by.belzhd.android.tickectchecker.data.PassengerTableFull;

public class FullTableAdapter extends RecyclerView.Adapter<FullTableAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(PassengerTableFull passenger);
    }

    private Context mContext;
    private List<PassengerTableFull> mPassengers;
    private OnItemClickListener mListener;

    public FullTableAdapter(Context context, List<PassengerTableFull> passengers, OnItemClickListener listener){
        mContext = context;
        mPassengers = passengers;
        mListener = listener;
    }

    @NonNull
    @Override
    public FullTableAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.full_table_layout_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FullTableAdapter.ViewHolder holder, int position) {
        PassengerTableFull passenger = mPassengers.get(position);
        holder.bindPassenger(passenger, mListener);
    }

    @Override
    public int getItemCount() {
        return mPassengers.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mSecondNameTextView;
        private TextView mInitialsTextView;
        private TextView mStatusTextView;

        private PassengerTableFull mPassenger;

        public void bindPassenger(final PassengerTableFull passenger, final OnItemClickListener listener) {
            mPassenger = passenger;
            mSecondNameTextView.setText(mPassenger.getSecondName());
            mInitialsTextView.setText(mPassenger.getInitials());
            mStatusTextView.setText(mPassenger.getStatus());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(mPassenger);
                }
            });
        }

        public ViewHolder(View itemView) {
            super(itemView);

            mSecondNameTextView = (TextView) itemView.findViewById(R.id.secondNameEditText);
            mInitialsTextView = (TextView) itemView.findViewById(R.id.firstNameEditText);
            mStatusTextView = (TextView) itemView.findViewById(R.id.statusEditText);
        }
    }
}

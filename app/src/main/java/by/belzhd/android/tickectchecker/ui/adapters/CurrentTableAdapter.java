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
import by.belzhd.android.tickectchecker.data.PassengerTableEntity;

public class CurrentTableAdapter extends RecyclerView.Adapter<CurrentTableAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onCurrentItemClick(PassengerTableEntity passenger);
    }

    private Context mContext;
    private List<PassengerTableEntity> mPassengers;
    private OnItemClickListener mListener;

    public CurrentTableAdapter(Context context, List<PassengerTableEntity> passengers, OnItemClickListener listener){
        mContext = context;
        mPassengers = passengers;
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.current_table_layout_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PassengerTableEntity passenger = mPassengers.get(position);
        holder.bindPassenger(passenger, mListener);
    }

    @Override
    public int getItemCount() {
        return mPassengers.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mSecondNameTextView;
        private TextView mInitialsTextView;

        private PassengerTableEntity mPassenger;

        public void bindPassenger(final PassengerTableEntity passenger, final OnItemClickListener listener) {
            mPassenger = passenger;
            mSecondNameTextView.setText(mPassenger.getSecondName());
            mInitialsTextView.setText(mPassenger.getInitials());

            itemView.setOnClickListener(view -> listener.onCurrentItemClick(mPassenger));
        }

        public ViewHolder(View itemView) {
            super(itemView);

            mSecondNameTextView = (TextView) itemView.findViewById(R.id.secondNameEditText);
            mInitialsTextView = (TextView) itemView.findViewById(R.id.firstNameEditText);
        }
    }
}

package by.belzhd.android.tickectchecker.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import by.belzhd.android.tickectchecker.R;
import by.belzhd.android.tickectchecker.data.PassengerDisemb;

import static by.belzhd.android.tickectchecker.utils.Constants.STATUS_ENTRY;
import static by.belzhd.android.tickectchecker.utils.Constants.STATUS_EXIT;

public class DisEmbAdapter extends RecyclerView.Adapter<DisEmbAdapter.ViewHolder> {

    public interface OnSwithed {
        void onPassengerChecked(PassengerDisemb passenger, boolean isChecked);
    }

    private Context mContext;
    private List<PassengerDisemb> mPassengers;
    private OnSwithed mListener;

    public DisEmbAdapter(Context mContext, List<PassengerDisemb> mPassengers, OnSwithed mListener) {
        this.mContext = mContext;
        this.mPassengers = mPassengers;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.disemb_table_layout_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PassengerDisemb passenger = mPassengers.get(position);
        holder.bindPassenger(passenger, mListener);
    }

    @Override
    public int getItemCount() {
        return mPassengers.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mSecondNameTextView;
        private TextView mInitialsTextView;
        private TextView mSeatCarriage;
        private Switch mStatusSwith;

        private PassengerDisemb passenger;

        public void bindPassenger(final PassengerDisemb passenger, final OnSwithed listener) {
            this.passenger = passenger;
            mSecondNameTextView.setText(this.passenger.getSecondName());
            mInitialsTextView.setText(this.passenger.getInitials());
            mSeatCarriage.setText(String.format(Locale.US, "%d/%d", this.passenger.getCarriageNumber(), this.passenger.getSeatNumber()));
            mStatusSwith.setChecked(false);

            mStatusSwith.setOnCheckedChangeListener((buttonView, isChecked) -> listener.onPassengerChecked(this.passenger, isChecked));
        }

        public ViewHolder(View itemView) {
            super(itemView);

            mSecondNameTextView = (TextView) itemView.findViewById(R.id.secondNameEditText);
            mInitialsTextView = (TextView) itemView.findViewById(R.id.firstNameEditText);
            mSeatCarriage = (TextView) itemView.findViewById(R.id.carriageSeatValue);
            mStatusSwith = (Switch) itemView.findViewById(R.id.swithStatus);
        }
    }
}

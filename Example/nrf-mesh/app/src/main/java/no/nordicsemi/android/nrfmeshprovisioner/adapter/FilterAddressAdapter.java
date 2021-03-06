/*
 * Copyright (c) 2018, Nordic Semiconductor
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its contributors may be used to endorse or promote products derived from this
 * software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE
 * USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package no.nordicsemi.android.nrfmeshprovisioner.adapter;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import no.nordicsemi.android.meshprovisioner.transport.ProvisionedMeshNode;
import no.nordicsemi.android.meshprovisioner.utils.AddressArray;
import no.nordicsemi.android.meshprovisioner.utils.MeshParserUtils;
import no.nordicsemi.android.meshprovisioner.utils.ProxyFilter;
import no.nordicsemi.android.nrfmeshprovisioner.R;
import no.nordicsemi.android.nrfmeshprovisioner.widgets.RemovableViewHolder;

public class FilterAddressAdapter extends RecyclerView.Adapter<FilterAddressAdapter.ViewHolder> {

    private final ArrayList<AddressArray> mAddresses;// = new ArrayList<>();
    private final Context mContext;
    private OnItemClickListener mOnItemClickListener;

    public FilterAddressAdapter(@NonNull final Context context, @NonNull final LiveData<ProvisionedMeshNode> meshNodeLiveData) {
        this.mContext = context;
        mAddresses = new ArrayList<>();
        meshNodeLiveData.observe((LifecycleOwner) context, meshNode -> {
            if (meshNode != null) {
                final ProxyFilter proxyFilter = meshNode.getProxyFilter();
                if (proxyFilter != null) {
                    mAddresses.clear();
                    mAddresses.addAll(proxyFilter.getAddresses());
                    notifyDataSetChanged();
                }
            }
        });
    }

    public void setOnItemClickListener(final FilterAddressAdapter.OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    @NonNull
    @Override
    public FilterAddressAdapter.ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        final View layoutView = LayoutInflater.from(mContext).inflate(R.layout.address_item, parent, false);
        return new FilterAddressAdapter.ViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull final FilterAddressAdapter.ViewHolder holder, final int position) {
        if (mAddresses.size() > 0) {
            final byte[] address = mAddresses.get(position).getAddress();
            holder.address.setText(MeshParserUtils.bytesToHex(address, true));
            if (MeshParserUtils.isValidSubscriptionAddress(address)) {
                holder.addressTitle.setText(R.string.title_group_address);
            } else if (MeshParserUtils.isValidUnicastAddress(address)) {
                holder.addressTitle.setText(R.string.title_unicast_address);
            } else {
                holder.addressTitle.setText(R.string.address);
            }
        }
    }

    @Override
    public long getItemId(final int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mAddresses.size();
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }

    @FunctionalInterface
    public interface OnItemClickListener {
        void onItemClick(final int position, final byte[] address);
    }

    public final class ViewHolder extends RemovableViewHolder {

        @BindView(R.id.address_id)
        TextView addressTitle;
        @BindView(R.id.address)
        TextView address;

        private ViewHolder(final View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.findViewById(R.id.removable).setOnClickListener(v -> {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(getAdapterPosition(), mAddresses.get(getAdapterPosition()).getAddress());
                }
            });
        }
    }
}

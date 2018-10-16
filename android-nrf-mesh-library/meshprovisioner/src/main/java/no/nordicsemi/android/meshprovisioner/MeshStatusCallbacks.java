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

package no.nordicsemi.android.meshprovisioner;

import android.support.annotation.NonNull;

import no.nordicsemi.android.meshprovisioner.message.MeshMessage;
import no.nordicsemi.android.meshprovisioner.message.ProvisionedMeshNode;

/**
 * Callbacks to notify the status of the mesh messgaes
 */
public interface MeshStatusCallbacks {

    /**
     * Notifies if a transaction has failed
     * <p>
     * As of now this is only triggered if the incomplete timer has expired for a given segmented message.
     * The incomplete timer will wait for a minimum of 10 seconds on receiving a segmented message.
     * If all segments are not received during this period, that transaction shall be considered as failed.
     * </p>
     *
     * @param node                      mesh node that failed to handle the transaction
     * @param src                       unique src address of the device
     * @param hasIncompleteTimerExpired flag that notifies if the incomplete timer had expired
     */
    void onTransactionFailed(final ProvisionedMeshNode node, final int src, final boolean hasIncompleteTimerExpired);

    /**
     * Notifies if an unknown pdu was received
     *
     * @param node mesh node that the message was received from
     */
    void onUnknownPduReceived(final ProvisionedMeshNode node);

    /**
     * Notifies if a block acknowledgement was sent
     *
     * @param node mesh node that the message was sent to
     */
    void onBlockAcknowledgementSent(final ProvisionedMeshNode node);

    /**
     * Notifies if a block acknowledgement was received
     *
     * @param node mesh node that the message was received from
     */
    void onBlockAcknowledgementReceived(final ProvisionedMeshNode node);

    /**
     * Callback to notify the mesh message has been sent
     *
     * @param meshMessage {@link MeshMessage} containing the message that was sent
     */
    void onMeshMessageSent(final MeshMessage meshMessage);

    /**
     * Callback to notify that a mesh status message was received
     *
     * @param meshMessage {@link MeshMessage} containing the message that was received
     */
    void onMeshMessageReceived(final MeshMessage meshMessage);
}

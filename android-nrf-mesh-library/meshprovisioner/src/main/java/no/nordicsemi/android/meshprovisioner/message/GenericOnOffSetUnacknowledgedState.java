package no.nordicsemi.android.meshprovisioner.message;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import no.nordicsemi.android.meshprovisioner.message.type.ControlMessage;
import no.nordicsemi.android.meshprovisioner.message.type.Message;

/**
 * State class for handling GenericOnOffSetState messages.
 */
class GenericOnOffSetUnacknowledgedState extends GenericMessageState {

    private static final String TAG = GenericOnOffSetUnacknowledgedState.class.getSimpleName();

    /**
     * Constructs {@link GenericOnOffSetState}
     *
     * @param context                       Context of the application
     * @param dstAddress                    Destination address to which the message must be sent to
     * @param genericOnOffSetUnacknowledged Wrapper class {@link GenericOnOffSetUnacknowledged} containing the opcode and parameters for {@link GenericOnOffSetUnacknowledged} message
     * @param callbacks                     {@link InternalMeshMsgHandlerCallbacks} for internal callbacks
     * @throws IllegalArgumentException for any illegal arguments provided.
     */
    GenericOnOffSetUnacknowledgedState(@NonNull final Context context,
                                              @NonNull final byte[] dstAddress,
                                              @NonNull final GenericOnOffSetUnacknowledged genericOnOffSetUnacknowledged,
                                              @NonNull final MeshTransport meshTransport,
                                              @NonNull final InternalMeshMsgHandlerCallbacks callbacks) throws IllegalArgumentException {
        super(context, dstAddress, genericOnOffSetUnacknowledged, meshTransport, callbacks);
        createAccessMessage();
    }

    @Override
    public MessageState getState() {
        return MessageState.GENERIC_ON_OFF_SET_UNACKNOWLEDGED_STATE;
    }

    /**
     * Creates the access message to be sent to the node
     */
    private void createAccessMessage() {
        final GenericOnOffSetUnacknowledged genericOnOffSetUnacknowledged = (GenericOnOffSetUnacknowledged) mMeshMessage;
        final byte[] key = genericOnOffSetUnacknowledged.getAppKey();
        final int akf = genericOnOffSetUnacknowledged.getAkf();
        final int aid = genericOnOffSetUnacknowledged.getAid();
        final int aszmic = genericOnOffSetUnacknowledged.getAszmic();
        final int opCode = genericOnOffSetUnacknowledged.getOpCode();
        final byte[] parameters = genericOnOffSetUnacknowledged.getParameters();
        message = mMeshTransport.createMeshMessage(mNode, mSrc, mDstAddress, key, akf, aid, aszmic, opCode, parameters);
        mPayloads.putAll(message.getNetworkPdu());
    }

    @Override
    public void executeSend() {
        Log.v(TAG, "Sending Generic OnOff set unacknowledged: ");
        super.executeSend();

        if (!mPayloads.isEmpty()) {
            if (mMeshStatusCallbacks != null)
                mMeshStatusCallbacks.onMeshMessageSent(mMeshMessage);
        }
    }
}

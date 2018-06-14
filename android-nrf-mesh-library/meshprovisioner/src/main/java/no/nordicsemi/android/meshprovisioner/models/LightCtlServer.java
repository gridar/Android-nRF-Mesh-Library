package no.nordicsemi.android.meshprovisioner.models;

import android.os.Parcel;

public class LightCtlServer extends SigModel {

    public static final Creator<LightCtlServer> CREATOR = new Creator<LightCtlServer>() {
        @Override
        public LightCtlServer createFromParcel(final Parcel source) {
            return new LightCtlServer(source);
        }

        @Override
        public LightCtlServer[] newArray(final int size) {
            return new LightCtlServer[size];
        }
    };

    public LightCtlServer(final int modelId) {
        super(modelId);
    }

    private LightCtlServer(final Parcel source) {
        super(source);
    }

    @Override
    public String getModelName() {
        return "Light Ctl Server";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        super.parcelMeshModel(dest, flags);
    }

}

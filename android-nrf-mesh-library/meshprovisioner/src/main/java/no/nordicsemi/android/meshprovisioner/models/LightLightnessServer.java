package no.nordicsemi.android.meshprovisioner.models;

import android.os.Parcel;

public class LightLightnessServer extends SigModel {

    public static final Creator<LightLightnessServer> CREATOR = new Creator<LightLightnessServer>() {
        @Override
        public LightLightnessServer createFromParcel(final Parcel source) {
            return new LightLightnessServer(source);
        }

        @Override
        public LightLightnessServer[] newArray(final int size) {
            return new LightLightnessServer[size];
        }
    };

    public LightLightnessServer(final int modelId) {
        super(modelId);
    }

    private LightLightnessServer(final Parcel source) {
        super(source);
    }

    @Override
    public String getModelName() {
        return "Light Lightness Server";
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

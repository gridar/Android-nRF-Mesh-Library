package no.nordicsemi.android.nrfmeshprovisioner.viewmodels;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import no.nordicsemi.android.meshprovisioner.transport.MeshMessage;

public class MeshMessageLiveData extends SingleLiveEvent<MeshMessage> {

    @Override
    public void postValue(final MeshMessage value) {
        super.postValue(value);
    }

    @Override
    public void observe(LifecycleOwner owner, Observer<? super MeshMessage> observer) {
        super.observe(owner, observer);
    }
}

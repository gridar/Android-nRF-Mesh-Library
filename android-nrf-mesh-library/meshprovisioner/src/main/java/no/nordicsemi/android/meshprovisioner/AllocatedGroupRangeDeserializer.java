package no.nordicsemi.android.meshprovisioner;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

import no.nordicsemi.android.meshprovisioner.transport.ApplicationKey;
import no.nordicsemi.android.meshprovisioner.utils.MeshParserUtils;

final class AllocatedGroupRangeDeserializer implements JsonSerializer<AllocatedGroupRange>, JsonDeserializer<AllocatedGroupRange> {
    private static final String TAG = AllocatedGroupRangeDeserializer.class.getSimpleName();

    @Override
    public JsonElement serialize(final AllocatedGroupRange src, final Type typeOfSrc, final JsonSerializationContext context) {
        return null;
    }

    @Override
    public AllocatedGroupRange deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) throws JsonParseException {
        final JsonObject jsonObject = json.getAsJsonObject();
        final int lowAddress = Integer.parseInt(jsonObject.get("lowAddress").getAsString(), 16);
        final int highAddress = Integer.parseInt(jsonObject.get("highAddress").getAsString(), 16);
        return new AllocatedGroupRange(lowAddress, highAddress);
    }
}
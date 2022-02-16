package io.kodesonik.sim;

import com.getcapacitor.JSObject;

import org.json.JSONArray;
import org.json.JSONException;

import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.annotation.Permission;
import com.getcapacitor.annotation.PermissionCallback;
import com.getcapacitor.PermissionState;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.telephony.SubscriptionManager;
import android.telephony.SubscriptionInfo;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CapacitorPlugin(name = "SimpleSim", permissions = {
        @Permission(
                alias = "sim",
                strings = {Manifest.permission.READ_PHONE_STATE}
        )
})

public class SimpleSimPlugin extends Plugin {
    private SimpleSim implementation = new SimpleSim();

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
    @PluginMethod()
    public void getSims(PluginCall call) throws JSONException {
        // String value = call.getString("filter");
        // Filter based on the value if want
        simsPermsCallback(call);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
    @PermissionCallback
    private void simsPermsCallback(PluginCall call) throws JSONException {
        if (getPermissionState("sim") == PermissionState.GRANTED) {
            loadSims(call);
        } else {
            requestAllPermissions(call, "simsPermsCallback");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
    void loadSims(PluginCall call) throws JSONException {
        if (getPermissionState("sim")!= PermissionState.GRANTED) {
            call.reject("Permission is required to read sims");
        } else {
            ArrayList<Map> simList = new ArrayList<>();
            Context context = this.getContext();
//            TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            SubscriptionManager subscriptionManager = (SubscriptionManager) context.getSystemService(Context.TELEPHONY_SUBSCRIPTION_SERVICE);
            @SuppressLint("MissingPermission") List<SubscriptionInfo> subscriptionInfos = subscriptionManager.getActiveSubscriptionInfoList();
            for (SubscriptionInfo subscriptionInfo : subscriptionInfos) {
    
              CharSequence carrierName = subscriptionInfo.getCarrierName();
              CharSequence displayName = subscriptionInfo.getDisplayName();
              int mcc = subscriptionInfo.getMcc();
              int mnc = subscriptionInfo.getMnc();
              int simSlotIndex = subscriptionInfo.getSimSlotIndex();
              Map simData = new HashMap<String, String>();
              simData.put("name", carrierName.toString());
              simData.put("displayName", displayName.toString());
              simData.put("mcc", mcc);
              simData.put("mnc", mnc);
              simData.put("id", simSlotIndex);
              simList.add(simData);
            }
            JSONArray jsonArray = new JSONArray(simList);
            JSObject ret = new JSObject();
            ret.put("result", jsonArray);
            call.resolve(ret);
        }
       
    }
}

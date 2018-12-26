package com.example.liudachuia.sophixdemo;

import android.content.Context;
import android.support.annotation.Keep;
import android.util.Log;

import com.taobao.sophix.PatchStatus;
import com.taobao.sophix.SophixApplication;
import com.taobao.sophix.SophixEntry;
import com.taobao.sophix.SophixManager;
import com.taobao.sophix.listener.PatchLoadStatusListener;

/**
 * Sophix入口类，专门用于初始化Sophix，不应包含任何业务逻辑。
 * 此类必须继承自SophixApplication，onCreate方法不需要实现。
 * 此类不应与项目中的其他类有任何互相调用的逻辑，必须完全做到隔离。
 * AndroidManifest中设置application为此类，而SophixEntry中设为原先Application类。
 * 注意原先Application里不需要再重复初始化Sophix，并且需要避免混淆原先Application类。
 * 如有其它自定义改造，请咨询官方后妥善处理。
 */
public class SophixStubApplication extends SophixApplication {
    private final String TAG = "SophixStubApplication";

    // 此处SophixEntry应指定真正的Application，并且保证RealApplicationStub类名不被混淆。
    @Keep
    @SophixEntry(MyApplication.class)
    static class RealApplicationStub {
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//         如果需要使用MultiDex，需要在此处调用。
//         MultiDex.install(this);
        initSophix();
    }

    private void initSophix() {
        String appVersion = "0.0.0";
        String channel = BuildConfig.channel;
        try {
            appVersion = this.getPackageManager()
                    .getPackageInfo(this.getPackageName(), 0)
                    .versionName;
        } catch (Exception e) {
        }
        final SophixManager instance = SophixManager.getInstance();
        instance.setContext(this)
                .setAppVersion(appVersion + channel)
                .setSecretMetaData("25443428", "0a49cae743763c61abb01ed7e0fe574b", "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCiK8vEQuwfUO1t2g0CGRamMubbLcdR/xeLdFUG/9tXHhMwd6cwpDKDtb3T4rZdtzsUNPtAZ+qmMmLzfsvrc4mSsy2n9OuoxbBhrCmh9aNS64MQzqNgFY2nlgwxvICmo7n9d75fCg2HFRBeoPLooZllO3Z4J9y804Kp8kLL6LJGLJsEWK369/DksTArDSgsVzxpOKplFL9GrXTFHDkEzxJ9tdVvqLrppHJ5gu1HOnkxedzua2zgK8kBT06zkj6weGFznJk7ttXc2q0zIShL0B/AzhcgRoFhe30TNzoZvID1tusIsQrNlqhUFd8C8zi12VRQYE6F16jEYyfhzZZ0TNDvAgMBAAECggEBAIKCiHwr92bEdC+B2hIkKqwaLtuZHd2JU2NxfbQiWFzl4T+SK8Tjf5uruVc6DtWgzjTW5pTQjHCwAB5Bns6frsmW1Vljq/upDOet69thmczzqAjwo16COfvyr2GfeFGjVdz8bwKBTp21f/UHxjUdSBvGHPNZ8TUtauxmAQek/0Ok/dx+7qNAQp1vmMTwBnAcxigrC1WedIqe7R4ZNXm2NazRoeNX8ZdVNUvzV5T2Y2bkOXbvSLwKcS8tULTUlS6nIv89kUuoZz2vkxpJx2gpKdaBPyJ0dui9z6/E0Jqgowft7TY8mKPGM6qiyrHsfxiVPuMKYEg5DpR8ul1/qepOByECgYEA1QmTeb0UfosVlgkWPEdYf7j7CBouIPsRSCUDBTx/MbNz9Fm9OGR+J8XpZeRP9ydDJu3fTxOoPEQWI2x58FYC8/nBjn77BRsPpCOCx0uJ6/jzfpMt8eJs5TBvr42ESF0+fYLSajosXFVJ6KCSPrwTD+XwtCJW/DkwL7PuHnBV+3cCgYEAwuAoaRvPcJ0kjm6wl7qJpujeh9sAggCIp040CUcpx1JrgwiIsPmC12EDh12ROZ/b04vqi03kON32+SgQJs7j+Fx8v8TZx0+gm/UkRKA+WUrm1Esa+xcB0OQgWsJ/qUTzdPLPQ/kRyQ3/JPLGKfSM1GrbYWljf6hpqHp5yMEHxEkCgYBK1G5E2LGWMWyHovF4gucHFga3Ndr2IrpTSQzijflvj8UvE0ZvxHy7o7zz2VptShFAX1WNBS6W3KOaFcRPMyceY6AlWVz60iKQWRjawCHWDUAaOcBtskblyjmwxFiuwnHszEL0pTJnzF2FpME53/4iG3aND3LWbZALcXw2nObWyQKBgAI+uNh1JI+kj85AQjKNSJauqhmgkUHuJaTfxsOwmS+dqHVq/MAlL7exe2vszQZ/6/Vk8D/Ilu98xyKZro3z5jZvQlSnPOcJ6MzDJzbPLcizjzM05kusOS4h4qmJAhV+INvhDDg5P1Y0nhLb5SKImbK8xGg74ZSP6R+ywYJpjuBZAoGBAKWPP1VtPcvZERlwFcAJpdH/6TYE2XH57uFJQ0z2WUFuipawr91aqDJkNw2ZU5iKstFVJA7iO4C5ERgOm+UK9XGXlkQd05awW5jQxkvsrivBQvgjC5885ERCf/UWRoqSeulH6+KiWsrhrFU/r9vqD4+w0QpZqwCgXVrsUAWPZbtz")
                .setEnableDebug(true)
                .setEnableFullLog()
                .setPatchLoadStatusStub(new PatchLoadStatusListener() {
                    @Override
                    public void onLoad(final int mode, final int code, final String info, final int handlePatchVersion) {
                        if (code == PatchStatus.CODE_LOAD_SUCCESS) {
                            Log.i(TAG, "sophix load patch success!");
                        } else if (code == PatchStatus.CODE_LOAD_RELAUNCH) {
                            // 如果需要在后台重启，建议此处用SharePreference保存状态。
                            Log.i(TAG, "sophix preload patch success. restart app to make effect.");
                        }
                    }
                }).initialize();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // queryAndLoadNewPatch不可放在attachBaseContext 中，否则无网络权限，建议放在      //后面任意时刻，如onCreate中
        SophixManager.getInstance().queryAndLoadNewPatch();
        /** 补丁在后台发布之后, 并不会主动下行推送到客户端, 客户端通过调用queryAndLoadNewPatch方法查询后台补丁是否可用*/
    }
}
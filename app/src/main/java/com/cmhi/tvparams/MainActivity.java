package com.cmhi.tvparams;

import static android.os.Build.*;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private TextView contentTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contentTv = findViewById(R.id.tv_content);

        double totalMem = getMemoryInfo(this).totalMem * 1.0/ (1024 * 1024);
        String totalStr = "内存大小为： " + totalMem + "MB\n";
        String cpuStr = "CPU名称为： " + CpuManager.getCpuName() + "\n";
        String cpuMax = "CPU最大值为(KHZ)： " + CpuManager.getMaxCpuFreq() + "\n";
        String cpuMin = "CPU最小值为(KHZ)： " + CpuManager.getMinCpuFreq() + "\n";
        String currentCpu = "CPU当前值为(KHZ)： " + CpuManager.getCurCpuFreq() + "\n";

        String sdcardTotalSize = "SdCard总大小为： " + getSDTotalSize() + "\n";
        String sdcardAvailableSize = "SdCard可用大小为： " + getSDAvailableSize() + "\n";
        String romTotalSize = "ROM总大小为： " + getRomTotalSize() + "\n";
        String romAvailableSize = "ROM可用大小为： " + getRomAvailableSize() + "\n";
        contentTv.setText(totalStr
                .concat(cpuStr).concat(cpuMax).concat(cpuMin).concat(currentCpu)
                .concat(sdcardTotalSize).concat(sdcardAvailableSize).concat(romTotalSize)
                .concat(romAvailableSize));

    }

    /**
     * Get memory info of device.
     */
    @TargetApi(VERSION_CODES.CUPCAKE)
    public static ActivityManager.MemoryInfo getMemoryInfo(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(mi);
        return mi;
    }

    /**
     * 获得SD卡总大小
     * @return
     */

    private String getSDTotalSize() {
        File path = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        return Formatter.formatFileSize(MainActivity.this, blockSize * totalBlocks);
    }

    /**
     * 获得sd卡剩余容量，即可用大小
     * @return
     */
    private String getSDAvailableSize() {
        File path = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return Formatter.formatFileSize(MainActivity.this, blockSize * availableBlocks);
    }

    /**
     * 获得机身内存总大小
     * @return
     */
    private String getRomTotalSize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        return Formatter.formatFileSize(MainActivity.this, blockSize * totalBlocks);
    }

    /**
     * 获得机身可用内存
     *
     * @return
     */
    private String getRomAvailableSize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return Formatter.formatFileSize(MainActivity.this, blockSize * availableBlocks);
    }

}

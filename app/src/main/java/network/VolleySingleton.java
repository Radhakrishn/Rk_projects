
package network;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HurlStack;

import utility.ImageCacheManager;

public class VolleySingleton {
	
	private static RequestQueue mRequestQueue;
	private static RequestQueue mImageRequestQueue;
	
	
	private VolleySingleton() {}
	
	/**
	 * initialize VolleySingleton
	 */
	public static void init(Context context) {
		mRequestQueue = com.android.volley.toolbox.Volley.newRequestQueue(context, new HurlStack());
		mImageRequestQueue = com.android.volley.toolbox.Volley.newRequestQueue(context);
		ImageCacheManager.INSTANCE.initImageCache();
	}
	
	public static RequestQueue getRequestQueue(Context context) {
		if (mRequestQueue != null) {
			return mRequestQueue;
		} else {
			return mRequestQueue = com.android.volley.toolbox.Volley.newRequestQueue(context);
		}
	}
	
	public static RequestQueue getImageRequestQueue() {

		if (mImageRequestQueue != null) {
			return mImageRequestQueue;
		} else {
			throw new IllegalStateException("Image RequestQueue not initialized");
		}
	}
}

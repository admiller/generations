package com.generations;

import java.io.IOException;

import android.app.ActionBar;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ConnectionActivity extends Activity {

	// Debugging
	private static final String TAG = "ConnectionActivity";
	private static final boolean D = true;

	// Intent request codes
	private static final int REQUEST_CONNECT_DEVICE_SECURE = 1;
	private static final int REQUEST_CONNECT_DEVICE_INSECURE = 2;
	private static final int REQUEST_ENABLE_BT = 3;

	// Key names received from the BluetoothChatService Handler
	public static final String DEVICE_NAME = "device_name";
	public static final String TOAST = "toast";

	// Message types sent from the BluetoothChatService Handler
	public static final int MESSAGE_STATE_CHANGE = 1;
	public static final int MESSAGE_READ = 2;
	public static final int MESSAGE_WRITE = 3;
	public static final int MESSAGE_DEVICE_NAME = 4;
	public static final int MESSAGE_TOAST = 5;

	// Local Bluetooth adapter
	private BluetoothAdapter mBluetoothAdapter = null;
	// Member object for the chat services
	private BluetoothService mService = null;

	// Name of the connected device
	private String mConnectedDeviceName = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_connection);
		// Show the Up button in the action bar.
		setupActionBar();
		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		if (mBluetoothAdapter == null) { // device does not support bluetooth
			Toast.makeText(getApplicationContext(),
					"Your Device does not appear to support Bluetooth",
					Toast.LENGTH_SHORT).show();
			finish();
		}
		// Launch the DeviceListActivity to see devices and do scan
		Intent serverIntent = new Intent(this, DeviceListActivity.class);
		startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE_SECURE);
		if (mService == null) {
			mService = new BluetoothService(this, mHandler);
		}
	}
	
    @Override
    public synchronized void onResume() {
        super.onResume();
        if(D) Log.e(TAG, "+ ON RESUME +");

        // Performing this check in onResume() covers the case in which BT was
        // not enabled during onStart(), so we were paused to enable it...
        // onResume() will be called when ACTION_REQUEST_ENABLE activity returns.
        if (mService != null) {
            // Only if the state is STATE_NONE, do we know that we haven't started already
            if (mService.getState() == BluetoothService.STATE_NONE) {
              // Start the Bluetooth chat services
              mService.start();
            }
        }
    }

	public void sendMainUser() {
		// Check that we're actually connected before trying anything
		if (mService.getState() != BluetoothService.STATE_CONNECTED) {
			Toast.makeText(this, R.string.not_connected, Toast.LENGTH_SHORT)
					.show();
			return;
		}

		mService.write(MainActivity.getUser());
		Log.i(TAG, "Wrote the User to mService");
	}

	// The Handler that gets information back from the BluetoothChatService
	private final Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case MESSAGE_STATE_CHANGE:
				if (D)
					Log.i(TAG, "MESSAGE_STATE_CHANGE: " + msg.arg1);
				switch (msg.arg1) {
				case BluetoothService.STATE_CONNECTED:
					setStatus("Connected to: " + mConnectedDeviceName);
					break;
				case BluetoothService.STATE_CONNECTING:
					setStatus(R.string.title_connecting);
					break;
				case BluetoothService.STATE_LISTEN:
				case BluetoothService.STATE_NONE:
					setStatus(R.string.title_not_connected);
					break;
				}
				break;
			case MESSAGE_WRITE:
				/*
				 * Should not have to worry about this byte[] writeBuf =
				 * (byte[]) msg.obj; // construct a string from the buffer
				 * String writeMessage = new String(writeBuf);
				 * mConversationArrayAdapter.add("Me:  " + writeMessage);
				 */
				break;
			case MESSAGE_READ:
				Person readPerson = null;
				try {
					readPerson = Person.deserialize((byte[]) msg.obj);
				} catch (IOException e) {
					e.printStackTrace();
					Log.e(TAG, "IO Exception in MESSAGE_READ");
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
					Log.e(TAG, "CNFException in MESSAGE_READ");
				}
				TextView tv1 = (TextView) findViewById(R.id.textView1);
				String info = "Error receiving person";
				if (readPerson != null) {
					info = "Name: " + readPerson.getName() + " Male: "
							+ readPerson.getMale() + "Parent 1 name: "
							+ readPerson.getParent(1).getName();
				}
				tv1.setText(info);
				// byte[] readBuf = (byte[]) msg.obj;
				// construct a string from the valid bytes in the buffer
				// String readMessage = new String(readBuf, 0, msg.arg1);
				// mConversationArrayAdapter.add(mConnectedDeviceName+":  " +
				// readMessage);
				break;
			case MESSAGE_DEVICE_NAME:
				// save the connected device's name
				mConnectedDeviceName = msg.getData().getString(DEVICE_NAME);
				Toast.makeText(getApplicationContext(),
						"Connected to " + mConnectedDeviceName,
						Toast.LENGTH_SHORT).show();
				break;
			case MESSAGE_TOAST:
				Toast.makeText(getApplicationContext(),
						msg.getData().getString(TOAST), Toast.LENGTH_SHORT)
						.show();
				break;
			}
		}
	};

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (D)
			Log.d(TAG, "onActivityResult " + resultCode);
		if(resultCode == Activity.RESULT_OK) {
			connectDevice(data, true);
		}
//		}
//		switch (requestCode) {
//		case REQUEST_CONNECT_DEVICE_SECURE:
//			// When DeviceListActivity returns with a device to connect
//			if (resultCode == Activity.RESULT_OK) {
//				connectDevice(data, true);
//			}
//			break;
//		case REQUEST_CONNECT_DEVICE_INSECURE:
//			// When DeviceListActivity returns with a device to connect
//			if (resultCode == Activity.RESULT_OK) {
//				connectDevice(data, false);
//			}
//			break;
//		}
	}

	private void connectDevice(Intent data, boolean secure) {
		// Get the device MAC address
		String address = data.getExtras().getString(
				DeviceListActivity.EXTRA_DEVICE_ADDRESS);
		// Get the BluetoothDevice object
		BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(address);
		// Attempt to connect to the device
		mService.connect(device, secure);
	}

	public void goBack(View view) {
		finish();
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private final void setStatus(CharSequence subTitle) {
		final ActionBar actionBar = getActionBar();
		actionBar.setSubtitle(subTitle);
	}

	private final void setStatus(int resId) {
		final ActionBar actionBar = getActionBar();
		actionBar.setSubtitle(resId);
	}

}

package mph.trunksku.apps.dexpro.view;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatEditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;
import java.io.File;
import kellinwood.logging.LoggerInterface;
import kellinwood.logging.LoggerManager;
import kellinwood.security.zipsigner.optional.CertCreator;
import kellinwood.security.zipsigner.optional.DistinguishedNameValues;
import kellinwood.security.zipsigner.optional.PasswordObfuscator;
import mph.trunksku.apps.dexpro.logger.LogFragment;
import mph.trunksku.apps.dexpro.utils.AlertDialogUtils;
public class CreateSignDialog
{

	private Context context;
	private AlertDialog mAlert;
    
    Params params = new Params();
    ProgressDialog creatingKeyProgressDialog = null;

    private static final String MSG_DATA_MESSAGE = "message";
    
	public CreateSignDialog(Context c)
	{
		context = c;
		ScrollView sv = new ScrollView(c);
        sv.setFillViewport(true);
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        LinearLayout ll = new LinearLayout(c);
        ll.setOrientation(1);
		ll.setPadding(25, 0, 25, 0);
        sv.setLayoutParams(layoutParams);
		final TextInputLayout til = new TextInputLayout(c);
		final AppCompatEditText acet = new AppCompatEditText(c);
		acet.setHint("Alias");
        acet.setText("dexprotect");
		acet.setSingleLine(true);
		til.addView(acet);
		final TextInputLayout til1 = new TextInputLayout(c);
		final AppCompatEditText acet1 = new AppCompatEditText(c);
		acet1.setHint("Password");
        acet1.setText("dexprotect");
		acet1.setSingleLine(true);
		til1.addView(acet1);
		final TextInputLayout til2 = new TextInputLayout(c);
		final AppCompatEditText acet2 = new AppCompatEditText(c);
		acet2.setHint("Common Name");
        acet2.setText("DexProtector");
		acet2.setSingleLine(true);
		til2.addView(acet2);
		final TextInputLayout til3 = new TextInputLayout(c);
		final AppCompatEditText acet3 = new AppCompatEditText(c);
		acet3.setHint("Organization");
        acet3.setText("DexPro");
		acet3.setSingleLine(true);
		til3.addView(acet3);
		final TextInputLayout til4 = new TextInputLayout(c);
		final AppCompatEditText acet4 = new AppCompatEditText(c);
		acet4.setHint("Organization Unit");
        acet4.setText("DexProDev");
		acet4.setSingleLine(true);
		til4.addView(acet4);
        
        final TextInputLayout til6 = new TextInputLayout(c);
        final AppCompatEditText acet6 = new AppCompatEditText(c);
        acet6.setHint("Locality");
        //acet6.setText("California");
        acet6.setSingleLine(true);
		til6.addView(acet6);
        
		final TextInputLayout til5 = new TextInputLayout(c);
		final AppCompatEditText acet5 = new AppCompatEditText(c);
		acet5.setHint("Country");
        //acet5.setText("United State");
		acet5.setSingleLine(true);
		til5.addView(acet5);
		

		ll.addView(til);
		ll.addView(til1);
		ll.addView(til2);
		ll.addView(til3);
		ll.addView(til4);
        ll.addView(til6);
   
		ll.addView(til5);
		
		sv.addView(ll);
		mAlert = new AlertDialog.Builder(c)
			.setTitle("Create KeyStore")
			//.setMessage("Enter a valid info")
			.setView(sv)
			.setPositiveButton("SAVE", new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface p1, int p2)
				{
					// TODO: Implement this method
					if (acet.getText().toString().isEmpty())
					{
						toast("Invalid or Empty Info");
					}
					else if (acet1.getText().toString().isEmpty())
					{
						toast("Invalid or Empty Info");
					}
					else if (acet2.getText().toString().isEmpty())
					{
						toast("Invalid or Empty Info");
					}
					else if (acet3.getText().toString().isEmpty())
					{
						toast("Invalid or Empty Info");
					}
					else if (acet4.getText().toString().isEmpty())
					{
						toast("Invalid or Empty Info");
					}
					else if (acet5.getText().toString().isEmpty())
					{
						toast("Invalid or Empty Info");
					}
					else if (acet6.getText().toString().isEmpty())
					{
						toast("Invalid or Empty Info");
					}
					else
					{
						save(acet.getText().toString(), acet1.getText().toString(), acet2.getText().toString(),  acet3.getText().toString(), acet4.getText().toString(), acet5.getText().toString(), acet6.getText().toString() );
					}
				}
			})
			.setNegativeButton("Cancel", null)
			.create();
	}

	void toast(String str)
	{
		Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
	}

	void save(String a, String b, String c, String d, String e, String f, String g)
	{
		File folder = new File(Environment.getExternalStorageDirectory() + "/DexProtect/key");
		if (!folder.exists())
		{
			folder.mkdir();
		}
		File keystoreFile = new File(folder.getAbsolutePath() + "/" + c.replace(" ", "_").toLowerCase() + ".dx");
		if (keystoreFile.exists())
		{
			toast("Keystore Already Exist!");
		}
		else
		{
            params = new Params();
            params.certValidityYears = 30;
            params.certSignatureAlgorithm = "SHA1withRSA" ;

            params.distinguishedNameValues.setCountry(f);
            params.distinguishedNameValues.setLocality(g);
            params.distinguishedNameValues.setOrganization(d);
            params.distinguishedNameValues.setOrganizationalUnit(e);
            params.distinguishedNameValues.setCommonName(c);

            params.storePath = keystoreFile.getAbsolutePath();
            params.storePass = PasswordObfuscator.getInstance().encodeKeystorePassword(keystoreFile.getAbsolutePath(), b);
            params.keyAlgorithm = "RSA";
            params.keySize = 2048;
            params.keyName = a;
            params.keyPass  = PasswordObfuscator.getInstance().encodeAliasPassword(keystoreFile.getAbsolutePath(), a, b);
            

            if (params.distinguishedNameValues.size() == 0) {
                AlertDialogUtils.alertDialog(context ,"Missing Info", "Please fill up all info");
                return;
            }

            

            doCreateKeystoreAndKey();
            
			/*CertCreator.DistinguishedNameValues cd = new CertCreator.DistinguishedNameValues();
			cd.setCommonName(c);
			cd.setOrganization(d);
			cd.setOrganizationalUnit(e);
			cd.setCountry(f);
			cd.setLocality(g);
			CertCreator.createKeystoreAndKey(keystoreFile.getAbsolutePath(), b.toCharArray(), "RSA", 2048, a, b.toCharArray(), "SHA1withRSA", 30, cd);
			toast("Creating Keystore Complete!");*/
		}
	}

	public void show()
	{
		mAlert.show();
	}
    
    
    
    private void doCreateKeystoreAndKey() {

        creatingKeyProgressDialog = new ProgressDialog(context);
        creatingKeyProgressDialog.setMessage("Creating Keystore...");
        creatingKeyProgressDialog.show();

        new Thread() {
            public void run() {
                LoggerInterface logger =  LoggerManager.getLogger(this.getClass().getName());
                
                char[] storePass = PasswordObfuscator.getInstance().decodeKeystorePassword( params.storePath, params.storePass);
                char[] keyPass = PasswordObfuscator.getInstance().decodeAliasPassword ( params.storePath, params.keyName, params.keyPass);
                try {
                    CertCreator.createKeystoreAndKey(params.storePath, storePass, params.keyName, params.distinguishedNameValues);
                  /*CertCreator.createKeystoreAndKey(params.storePath, storePass,
                                                     params.keyAlgorithm, params.keySize, params.keyName, keyPass,
                                                     params.certSignatureAlgorithm, params.certValidityYears, params.distinguishedNameValues);*/

                    sendMessage(MESSAGE_CODE_SUCCESS, "Create Success");

                } catch (Exception x) {
                    logger.error( x.getMessage(), x);
                    sendMessage(MESSAGE_CODE_FAILURE, x.getMessage());
                } finally {
                    PasswordObfuscator.flush(storePass);
                    PasswordObfuscator.flush(keyPass);
                }
            }

            void sendMessage( int msgCode, String message) {
                Message msg = new Message();
                msg.what = msgCode;
                Bundle data = new Bundle();
                data.putString( MSG_DATA_MESSAGE, message);
                msg.setData(data);
                handler.sendMessage(msg);
            }

        }.start();
    }


    private static final int MESSAGE_CODE_SUCCESS = 1;
    private static final int MESSAGE_CODE_FAILURE = 2;

    final Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_CODE_SUCCESS:
                    creatingKeyProgressDialog.dismiss();
                    toast("Creating Keystore Complete!");
                    break;
                case MESSAGE_CODE_FAILURE:
                    creatingKeyProgressDialog.dismiss();
                    LogFragment.addLog("Keystore: " + msg.getData().getString(MSG_DATA_MESSAGE));
                    toast("Creating Keystore Fail! "  + msg.getData().getString(MSG_DATA_MESSAGE));
                   // logger.error(msg.getData().getString(CreateCertFormActivity.MSG_DATA_MESSAGE));
                    break;
            }
        }
    };



    class Params {
       
        String storePath;
        String storePass;
        String keyName;
        String keyAlgorithm;
        int keySize;
        String keyPass;
        int certValidityYears;
        String certSignatureAlgorithm;
        DistinguishedNameValues distinguishedNameValues = new DistinguishedNameValues();


    }
}

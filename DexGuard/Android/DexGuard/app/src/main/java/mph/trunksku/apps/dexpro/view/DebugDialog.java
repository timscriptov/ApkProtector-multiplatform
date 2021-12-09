package mph.trunksku.apps.dexpro.view;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.widget.LinearLayout;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;
import mph.trunksku.apps.dexpro.logger.DebugLog;


public class DebugDialog {

	private static AndroidTreeView tView;
    
    public static void show(Context context){
		
		LinearLayout ll = new LinearLayout(context);
		TreeNode root = TreeNode.root();
		TreeNode computerRoot = null;
		String[] log = DebugLog.dump().split("\n");
		for ( int i = 0; i < log.length;  i++ ){
			
			if(log[i].startsWith("> ")){
				computerRoot = new TreeNode(log[i]);
				root.addChildren(computerRoot);
			}else{
				TreeNode file1 = new TreeNode(log[i]);
				if(computerRoot == null){
					root.addChildren(file1);
				}else{
					computerRoot.addChildren(file1);
				}
				
			}
		}
       /* TreeNode computerRoot = new TreeNode("Root");

        //TreeNode myDocuments = new TreeNode("Doc");
        TreeNode downloads = new TreeNode("Download");
        TreeNode file1 = new TreeNode("FIle");
        TreeNode file2 = new TreeNode("File 2");
       
        downloads.addChildren(file1, file2);

        TreeNode myMedia = new TreeNode("Media");
        TreeNode photo1 = new TreeNode("File");
            myMedia.addChildren(photo1);

        //myDocuments.addChild(downloads);
        computerRoot.addChildren(downloads, myMedia);

        root.addChildren(computerRoot);*/

        tView = new AndroidTreeView(context, root);
        tView.setDefaultAnimation(true);
        //tView.setDefaultContainerStyle(R.style.TreeNodeStyleCustom);
		ll.addView(tView.getView());
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Debug Log");
        builder.setView(ll, 10, 0, 10, 0);
        builder.setPositiveButton("ok", null);
        //builder.setNegativeButton("CANCEL", (DialogInterface.OnClickListener) null);
        builder.create().show();
	}
    
}

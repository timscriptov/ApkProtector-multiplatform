package mph.trunksku.apps.dexpro.dexmerger;

import java.io.PrintStream;

public class DxContext {
    public PrintStream err = System.err;
    public PrintStream out = System.out;

    public DxContext(){
        
    }
    
    public DxContext(PrintStream printStream, PrintStream printStream2) {
        this.out = printStream;
        this.err = printStream2;
    }
}


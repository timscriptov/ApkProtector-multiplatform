package mph.trunksku.apps.dexpro.model;

public  class ReplaceItem {

    public String icon = "";

    public String title = "";

    public ReplaceItem(String oldStr, String newStr){
        this.icon = oldStr;
        this.title = newStr;
    }



    public String getOld(){
        return icon;
    }

    public String getNew(){
        return title;
    }
}

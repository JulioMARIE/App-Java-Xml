package Model;

public class Admin {

    private long idAdmin;
    private String nomassociation;
    private String nomuser;
    private String pwd;
    private boolean checked;

    public Admin() {

    }

    public Admin(String nomassociation, String nomuser, String pwd) {
        this.nomassociation = nomassociation;
        this.nomuser = nomuser;
        this.pwd = pwd;
    }

    public long getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(long idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getNomassociation() {
        return nomassociation;
    }

    public void setNomassociation(String nomassociation) {
        this.nomassociation = nomassociation;
    }

    public String getNomuser() {
        return nomuser;
    }

    public void setNomuser(String nomuser) {
        this.nomuser = nomuser;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}

package sample;

/**
 *Klasse Books um eine Einladung zu senden
 */
public class Books {

    /**
     * Variable deklariern : id , source (Sender),dest (Empfänger), datet (Datum), details , apprv (Status)
     */
    int id;
    String sou, dest, datet, details, apprv;

    /**
     * Setter Methoden
     */
    public void setId(int Id){this.id = Id;}
    public void setSou(String Sou){this.sou = Sou;}
    public void setDest(String Dest){this.dest =Dest;}
    public void setDatet(String Datet){this.datet = Datet;}
    public void setDetails(String Details){this.details =Details;}
    public void setAppr(String Apprv){this.apprv =apprv;}

    /**
     * Getter Methoden
     */
    public int getId() { return id; }
    public String getSou() { return sou; }
    public String getDest() {return dest; }
    public String getDatet() { return datet; }
    public String getDetails() { return details; }
    public String getApprv() { return apprv; }

    /**
     * Konstruktor um Einladung zu senden
     * @param Id
     * @param Sou
     * @param Dest
     * @param Datet
     * @param Details
     * @param Apprv
     */
    public Books(int Id, String Sou, String Dest, String Datet, String Details, String Apprv){
        this.id = Id;
        this.sou=Sou;
        this.dest = Dest;
        this.datet=Datet;
        this.details=Details;
        this.apprv= Apprv;
    }


}

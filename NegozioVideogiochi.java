import java.io.*;
import java.sql.*;
import java.util.*;
import java.text.DateFormat;
import java.text.ParseException;

public class NegozioVideogiochi 
{   
	public static void main(String args[]) throws Exception 
    {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try 
        {
            con = DBConnectionPool.getConnection(); 
            st = con.createStatement();
        } 
        catch(SQLException s) 
        {
            System.out.println("Errore\n" + s);
        }
		InputStreamReader keyIS;
		BufferedReader keyBR;
		keyIS = new InputStreamReader(System.in);
		keyBR = new BufferedReader(keyIS);
        int i = 0;
		String scelta;

		while (i != 1000) 
        {
			System.out.println("Che operazione vuoi eseguire?");
			System.out.println("1, Aggiungere un nuovo Prodotto di tipo Hardware");
			System.out.println("2, Aggiungi un nuovo Cliente che effettua un acquisto");
            System.out.println("3, Registra una nuova Tessera Fedeltà relativa a un cliente");
            System.out.println("4, Stampare il nome, il PEGI, la descrizione e la categoria dei Videogiochi sviluppati da una specifica Software House");
            System.out.println("5, Aggiungere un nuovo Dipendente");
            System.out.println("6, Stampare il numero di Prodotti acquistati dai ogni Cliente");
            System.out.println("7, Stampare il nome dei Prodotti disponibili");
            System.out.println("8, Stampa per ogni tipologia il numero di prodotti hardware disponibili");
            System.out.println("9, Aggiungere un nuovo Fornitore che ha rifornito il negozio con un nuovo prodotto di tipo hardware");
            System.out.println("10, Aggiornare il prezzo a un prodotto");
            System.out.println("11, Aggiungere un acquisto applicando uno sconto e sottrarre dai punti ottenuti dal Cliente quelli utilizzati per riscattare lo sconto");
            System.out.println("12, Cancella i prodotti la cui disponibilità è uguale a 0");
            System.out.println("13, Stampa i dati anagrafici del cliente che possiede il maggior numero di Punti ottenuti");
            System.out.println("14, Stampa il nome e il cognome del cliente che ha acquistato uno specifico prodotto ma non ha acquistato un altro prodotto specifico");
            System.out.println("15, Stampa per ogni cliente la somma totale spesa in negozio");
            System.out.println("16, Stampa per una specifica Piattaforma i videogiochi per lei disponibili");
			System.out.println("1000, Per uscire");
			System.out.print("Inserisci scelta: ");
			scelta = keyBR.readLine();
			try 
            {
				i = Integer.parseInt(scelta);
			} 
            catch (NumberFormatException e) 
            {
				i = 999;
			}
			switch (i) 
            {
                case 1: 
                    {
                        Operazione1(con, st, rs);
                        break;
                    }
                case 2: 
                    {
                        Operazione2(con, st, rs);
                        break;
                    }
                case 3: 
                    {
                        Operazione3(con, st, rs);
                        break;
                    }
                case 4: 
                    {
                        Operazione4(con, st, rs);
                        break;
                    }
                case 5: 
                    {
                        Operazione5(con, st, rs);
                        break;
                    }
                case 6: 
                    {
                        Operazione6(st, rs);
                        break;
                    }
                case 7: 
                    {
                        Operazione7(st, rs);
                        break;
                    }
                case 8: 
                    {
                        Operazione8(st, rs);
                        break;
                    }
                case 9: 
                    {
                        Operazione9(con, st, rs);
                        break;
                    }
                case 10: 
                    {
                        Operazione10(con, st, rs);
                        break;
                    }
                case 11: 
                    {
                        Operazione11(con, st, rs);
                        break;
                    }
                case 12: 
                    {
                        Operazione12(con, st, rs);
                        break;
                    }
                case 13: 
                    {
                        Operazione13(st, rs);
                        break;
                    }
                case 14: 
                    {
                        Operazione14(con, st, rs);
                        break;
                    }
                case 15: 
                    {
                        Operazione15(st, rs);
                        break;
                    }
                case 16: 
                {
                    Operazione16(con, st, rs);
                    break;
                }
                case 1000: 
                    {
                        System.out.println("Uscita");
                        break;
                    }
                default: 
                    {
                        System.out.println("Scelta non presente");
                        break;
                    }
             } 
        }
        try 
        {
            if(rs != null) rs.close();
            if(st != null) st.close();
            DBConnectionPool.releaseConnection(con);
        } 
        catch(SQLException s) 
        {
            System.out.println("Errore\n" + s);
        }
	}
    
    
    
    public static void Operazione1(Connection con, Statement st, ResultSet rs) throws Exception
    {
        try
        {            
            BufferedReader Read = new BufferedReader(new InputStreamReader(System.in));
            int i=0;
            String CD=null;
            String CodDipendente[] = new String[255];
            String sql = "SELECT CodDipendente FROM Dipendente";
            rs = st.executeQuery(sql);
            while (rs.next())  
            {
                CodDipendente[i] = rs.getString("CodDipendente");
                i++;
            }
            i=0;
            System.out.printf("Per eseguire questa operazione mi serve il tuo CodDipendente, inseriscilo: ");
            while(CD == null)
            {
                CD = Read.readLine();              
                while(!(CD.equals(CodDipendente[i])))
                {
                    i++;
                    if (CodDipendente[i]==null) break;
                }
                if(!(CD.equals(CodDipendente[i])))
                {
                    i=0;
                    CD=null;
                    System.out.printf("CodDipendente non presente. Riprova: ");
                }                
            }
            i=0;
            String CodFornitore[] = new String[255];
            sql = "SELECT CodFornitore, Nome FROM Fornitore";
            rs = st.executeQuery(sql);
            System.out.printf("Questi sono i fornitori che riforniscono il negozio: \n");
            while (rs.next())  
            {
                CodFornitore[i] = rs.getString("CodFornitore");
                String NomeF = rs.getString("Nome");
                System.out.printf("%s %s\n",CodFornitore[i], NomeF);
                i++;
            }
            i=0;
            System.out.printf("Inseriscimi il CodFornitore del fornitore che ti ha rifornito il nuovo prodotto: ");
            String CFor = null;
            while(CFor == null)
            {
                CFor = Read.readLine();              
                while(!(CFor.equals(CodFornitore[i])))
                {
                    i++;
                    if (CodFornitore[i]==null) break;
                }
                if(!(CFor.equals(CodFornitore[i])))
                {
                    i=0;
                    CFor=null;
                    System.out.printf("Hai sbagliato a digitare. Riprova: ");
                }                
            } 
            String CodSeriale, Nome, DisponibilitàS, PrezzoS, DataUscitaS, DataUscita;
            int Disponibilità;
            Float Prezzo;
            System.out.printf("Dammi il codSeriale del nuovo prodotto: ");
            do
            {                
                CodSeriale = Read.readLine();
                if (CodSeriale.length()>20)
                {
                    System.out.printf("CodSeriale troppo lungo (max 20 caratteri), riprova: ");
                }
                else break;
            }while(true); 
            System.out.printf("Dammi il Nome del nuovo prodotto: ");
            do
            {                
                Nome = Read.readLine();
                if (Nome.length()>50)
                {
                    System.out.printf("Nome troppo lungo (max 50 caratteri), riprova: ");
                }
                else break;
            }while(true); 
            System.out.printf("Dimmi la quantità in cifre che ti è stata rifornita del nuovo prodotto: ");
            DisponibilitàS = Read.readLine();
            Disponibilità = Integer.parseInt(DisponibilitàS);
            System.out.printf("Dammi il Prezzo del nuovo prodotto: ");
            PrezzoS = Read.readLine();
            Prezzo = Float.parseFloat(PrezzoS);
            System.out.printf("Dammi la Data di Uscita del nuovo prodotto nel formato 'AAAA-MM-GG': ");
            do
            {
                do
                {  
                    DataUscitaS = Read.readLine();
                    if(DataUscitaS.length()<10 || DataUscitaS.length()>10)
                    {
                       System.out.printf("Il formato della data non è corretto riprova: "); 
                    }
                    else break;
                }while(true);
                int anno = Integer.parseInt(DataUscitaS.substring(0, 4));
                int mese = Integer.parseInt(DataUscitaS.substring(5, 7));
                int giorno = Integer.parseInt(DataUscitaS.substring(8, 10));
                GregorianCalendar cal = new GregorianCalendar (anno, mese-1, giorno);
                cal.setLenient (false);
                try
                {
                    if (cal.get(Calendar.DATE)!='0')
                    {
                        java.sql.Date d = new java.sql.Date(cal.getTimeInMillis());
                        DataUscita = d.toString();
                        break;
                    }
                }
                catch (Exception e) 
                {
                    System.out.printf("Il formato della data non è corretto riprova: ");
                }
            }while(true);        
            sql = "INSERT INTO Prodotto(CodSeriale, Nome, Disponibilità, Prezzo, DataUscita, CodFornitore) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, CodSeriale);
            ps.setString(2, Nome);
            ps.setInt(3, Disponibilità);
            ps.setFloat(4, Prezzo);
            ps.setString(5, DataUscita);
            ps.setString(6, CFor);
            int rs1 = ps.executeUpdate();
            
            String Tipologia, Edizione;
            System.out.printf("Dammi la tipologia del nuovo prodotto di tipo Hardware: ");
            do
            {                
                Tipologia = Read.readLine();
                if (Tipologia.length()>50)
                {
                    System.out.printf("Tipologia troppo lunga (max 50 caratteri), riprova: ");
                }
                else break;
            }while(true); 
            System.out.printf("Dammi l'edizione del nuovo prodotto di tipo Hardware: ");
            do
            {                
                Edizione = Read.readLine();
                if (Edizione.length()>50)
                {
                    System.out.printf("Edizione troppo lunga (max 50 caratteri), riprova: ");
                }
                else break;
            }while(true); 
            sql = "INSERT INTO Hardware(CodSeriale, Tipologia, Edizione) VALUES (?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, CodSeriale);
            ps.setString(2, Tipologia);
            ps.setString(3, Edizione);
            int rs2 = ps.executeUpdate();
            
            
            Calendar cal = Calendar.getInstance();
            java.sql.Date d = new java.sql.Date(cal.getTimeInMillis());
            String data = d.toString();
            sql = "INSERT INTO Modifica(DataModifica, CodDipendente, CodSeriale) VALUES (?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, data);
            ps.setString(2, CD);
            ps.setString(3, CodSeriale);
            int rs3 = ps.executeUpdate();
            con.commit();
            System.out.printf("Operazione eseguita con successo!\n");
        }
        catch (SQLException s) 
        {
            System.out.println("Errore\n" + s);
        }
        
    }
    
    
    
    
    public static void Operazione2(Connection con, Statement st, ResultSet rs) throws Exception
    {
        try
        {
            BufferedReader Read = new BufferedReader(new InputStreamReader(System.in));
            int i=0;
            String CD=null;
            String CodDipendente[] = new String[255];
            String sql = "SELECT CodDipendente FROM Dipendente";
            rs = st.executeQuery(sql);
            while (rs.next())  
            {
                CodDipendente[i] = rs.getString("CodDipendente");
                i++;
            }
            i=0;
            System.out.printf("Per eseguire questa operazione mi serve il tuo CodDipendente, inseriscilo: ");
            while(CD == null)
            {
                CD = Read.readLine();              
                while(!(CD.equals(CodDipendente[i])))
                {
                    i++;
                    if (CodDipendente[i]==null) break;
                }
                if(!(CD.equals(CodDipendente[i])))
                {
                    i=0;
                    CD=null;
                    System.out.printf("CodDipendente non presente. Riprova: ");
                }                
            }
            String CodiceFiscale, Nome, Cognome, Via, Città, Telefono, CivicoS;
            int Civico;
            System.out.printf("Dammi il CodiceFiscale del nuovo cliente: ");
            do
            {                
                CodiceFiscale = Read.readLine();
                if (!(CodiceFiscale.length()==16))
                {
                    System.out.printf("CodiceFiscale errato (deve essere di 16 caratteri), riprova: ");
                }
                else break;
            }while(true); 
            System.out.printf("Dammi il Nome del nuovo cliente: ");
            do
            {                
                Nome = Read.readLine();
                if (Nome.length()>50)
                {
                    System.out.printf("Nome troppo lungo (max 50 caratteri), riprova: ");
                }
                else break;
            }while(true);
            System.out.printf("Dammi il Cognome del nuovo cliente: ");
            do
            {                
                Cognome = Read.readLine();
                if (Cognome.length()>50)
                {
                    System.out.printf("Cognome troppo lungo (max 50 caratteri), riprova: ");
                }
                else break;
            }while(true);
            System.out.printf("Dammi la Via del nuovo cliente: ");
            do
            {                
                Via = Read.readLine();
                if (Via.length()>20)
                {
                    System.out.printf("Via troppo lunga (max 20 caratteri), riprova: ");
                }
                else break;
            }while(true);
            System.out.printf("Dammi il Civico del nuovo cliente: ");
            CivicoS = Read.readLine();
            Civico = Integer.parseInt(CivicoS);
            System.out.printf("Dammi la Città del nuovo cliente: ");
            do
            {                
                Città = Read.readLine();
                if (Città.length()>50)
                {
                    System.out.printf("Città troppo lungo (max 50 caratteri), riprova: ");
                }
                else break;
            }while(true);
            System.out.printf("Dammi il numero di telefono del nuovo cliente compreso il prefisso: ");
            do
            {                
                Telefono = Read.readLine();
                if (Telefono.length()<13 || Telefono.length()>20)
                {
                    System.out.printf("Numero di Telefono sbagliato, riprova: ");
                }
                else break;
            }while(true);
            sql = "INSERT INTO Cliente(CodiceFiscale, Nome, Cognome, Via, Civico, Città, Telefono, NumProdottiAcquistati) VALUES (?,?,?,?,?,?,?,1)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, CodiceFiscale);
            ps.setString(2, Nome);
            ps.setString(3, Cognome);
            ps.setString(4, Via);
            ps.setInt(5, Civico);
            ps.setString(6, Città);
            ps.setString(7, Telefono);
            int rs1 = ps.executeUpdate();
            
            
            i=0;
            String CodSeriale[] = new String[255];
            sql = "SELECT CodSeriale, Nome FROM Prodotto WHERE Disponibilità > 0";
            rs = st.executeQuery(sql);
            System.out.printf("Questi sono i prodotti presenti in negozio: \n");
            while (rs.next())  
            {
                CodSeriale[i] = rs.getString("CodSeriale");
                String nomeP = rs.getString("Nome");
                System.out.printf("%s %s\n",CodSeriale[i], nomeP);
                i++;
            }
            i=0;
            System.out.printf("Inseriscimi il CodSeriale del prodotto che il cliente ha acquistato: ");
            String CP = null;
            while(CP == null)
            {
                CP = Read.readLine();              
                while(!(CP.equals(CodSeriale[i])))
                {
                    i++;
                    if (CodSeriale[i]==null) break;
                }
                if(!(CP.equals(CodSeriale[i])))
                {
                    i=0;
                    CP=null;
                    System.out.printf("Hai sbagliato a digitare. Riprova: ");
                }                
            } 
            Calendar cal = Calendar.getInstance();
            java.sql.Date d = new java.sql.Date(cal.getTimeInMillis());
            String data = d.toString();
            sql = "INSERT INTO Acquisto(PercentualeSconto, DataAcquisto, CodiceFiscale, CodSeriale) VALUES (0,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, data);
            ps.setString(2, CodiceFiscale);
            ps.setString(3, CP);
            int rs2 = ps.executeUpdate();
            
            
            sql = "UPDATE Prodotto SET Disponibilità = Disponibilità - 1 WHERE CodSeriale = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, CP);
            int rs3 = ps.executeUpdate();
            

            sql = "INSERT INTO Modifica(DataModifica, CodDipendente, CodSeriale) VALUES (?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, data);
            ps.setString(2, CD);
            ps.setString(3, CP);
            int rs4 = ps.executeUpdate();
            
            con.commit();
            System.out.printf("Operazione eseguita con successo!\n");
        }
        catch (SQLException s) 
        {
            System.out.println("Errore\n" + s);
        }
         
    }
    
    
    
    public static void Operazione3(Connection con, Statement st, ResultSet rs) throws Exception
    {
        try
        {
            BufferedReader Read = new BufferedReader(new InputStreamReader(System.in));
            int i=0;
            String CodiceFiscale[] = new String[255];
            String sql = "SELECT CodiceFiscale, Nome, Cognome FROM Cliente WHERE CodiceFiscale NOT IN (SELECT CodiceFiscale FROM TesseraFedeltà)";
            rs = st.executeQuery(sql);
            System.out.printf("Questi sono i clienti che non posseggono una Tessera Fedeltà: \n");
            while (rs.next())  
            {
                CodiceFiscale[i] = rs.getString("CodiceFiscale");
                String NomeC = rs.getString("Nome");
                String CognomeC = rs.getString("Cognome");
                System.out.printf("%s %s %s\n",CodiceFiscale[i], NomeC, CognomeC);
                i++;
            }
            i=0;
            System.out.printf("Inseriscimi il Codice Fiscale del cliente a cui registare la nuova Tessera Fedeltà: ");
            String CF = null;
            while(CF == null)
            {
                CF = Read.readLine();              
                while(!(CF.equals(CodiceFiscale[i])))
                {
                    i++;
                    if (CodiceFiscale[i]==null) break;
                }
                if(!(CF.equals(CodiceFiscale[i])))
                {
                    i=0;
                    CF=null;
                    System.out.printf("Hai sbagliato a digitare. Riprova: ");
                }                
            }  
            Calendar cal = Calendar.getInstance();
            cal.add(cal.YEAR, 2);
            java.sql.Date d = new java.sql.Date(cal.getTimeInMillis());
            String data = d.toString();
            sql = "INSERT INTO TesseraFedeltà(CodiceFiscale, Scadenza, PuntiOttenuti) VALUES (?,?,'0')";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, CF);
            ps.setString(2, data);
            int rs1 = ps.executeUpdate();
            con.commit();
            System.out.printf("Operazione eseguita con successo!\n");
        }
        catch (SQLException s) 
        {
            System.out.println("Errore\n" + s);
        }
        
        
    }
    
    
    
    
    
    public static void Operazione4(Connection con, Statement st, ResultSet rs) throws Exception
    {
        try
        {
            BufferedReader Read = new BufferedReader(new InputStreamReader(System.in));
            int i=0;
            String PartitaIVA[] = new String[255];
            String sql = "SELECT PartitaIVA, Nome FROM SoftwareHouse";
            rs = st.executeQuery(sql);
            System.out.printf("Questi sono i nomi delle SoftwareHouse: \n");
            System.out.printf("PartitaIva  Nome\n");
            while (rs.next())  
            {
                PartitaIVA[i] = rs.getString("PartitaIVA");
                String Nome = rs.getString("Nome");
                System.out.printf("%s %s\n",PartitaIVA[i], Nome);
                i++;
            }
            i=0;
            System.out.printf("Inseriscimi la PartitaIVA della SoftwareHouse di cui vuoi vedere i giochi sviluppati: ");
            String PIVA = null;
            while(PIVA == null)
            {
                PIVA = Read.readLine();              
                while(!(PIVA.equals(PartitaIVA[i])))
                {
                    i++;
                    if (PartitaIVA[i]==null) break;
                }
                if(!(PIVA.equals(PartitaIVA[i])))
                {
                    i=0;
                    PIVA=null;
                    System.out.printf("Hai sbagliato a digitare. Riprova: ");
                }                
            }
            
            sql = "SELECT P.Nome, V.PEGI, V.Descrizione, V.Categoria FROM Prodotto AS P, Videogioco AS V, Sviluppa AS S WHERE S.PartitaIVA = ? AND S.CodSeriale = V.CodSeriale AND V.CodSeriale = P.CodSeriale";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, PIVA);
            rs = ps.executeQuery();
            System.out.printf("Nome  PEGI  Descrizione Categoria\n");
            while (rs.next())  
            {
                String Nome = rs.getString("Nome");
                int PEGI = rs.getInt("PEGI");
                String Descrizione = rs.getString("Descrizione");
                String Categoria = rs.getString("Categoria");
                System.out.printf("%s %d %s %s\n",Nome, PEGI, Descrizione, Categoria);
            }
        }
        catch (SQLException s) 
        {
            System.out.println("Errore\n" + s);
        }
        
    }
    
    
    
    
    
    public static void Operazione5(Connection con, Statement st, ResultSet rs) throws Exception
    {
        try
        {
            BufferedReader Read = new BufferedReader(new InputStreamReader(System.in));
            String CodDipendente, Nome, Cognome, Telefono, StipendioS;
            Float Stipendio;
            System.out.printf("Dammi il CodDipendente del nuovo dipendente: ");
            do
            {                
                CodDipendente = Read.readLine();
                if (CodDipendente.length()>20)
                {
                    System.out.printf("CodDipendente troppo lungo (max 20 caratteri), riprova: ");
                }
                else break;
            }while(true); 
            System.out.printf("Dammi il Nome del nuovo dipendente: ");
            do
            {                
                Nome = Read.readLine();
                if (Nome.length()>50)
                {
                    System.out.printf("Nome troppo lungo (max 50 caratteri), riprova: ");
                }
                else break;
            }while(true);
            System.out.printf("Dammi il Cognome del nuovo dipendente: ");
            do
            {                
                Cognome = Read.readLine();
                if (Cognome.length()>50)
                {
                    System.out.printf("Cognome troppo lungo (max 50 caratteri), riprova: ");
                }
                else break;
            }while(true);
            System.out.printf("Dammi il numero di telefono del nuovo dipendente compreso il prefisso: ");
            do
            {                
                Telefono = Read.readLine();
                if (Telefono.length()<13 || Telefono.length()>20)
                {
                    System.out.printf("Numero di Telefono sbagliato, riprova: ");
                }
                else break;
            }while(true);
            System.out.printf("Dammi lo stipendio del nuovo dipendente: ");
            StipendioS = Read.readLine();
            Stipendio = Float.parseFloat(StipendioS);     
            String sql = "INSERT INTO Dipendente(CodDipendente, Nome, Cognome, Stipendio, Telefono) VALUES(?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, CodDipendente);
            ps.setString(2, Nome);
            ps.setString(3, Cognome);
            ps.setFloat(4, Stipendio);
            ps.setString(5, Telefono);
            int rs1 = ps.executeUpdate();
            con.commit();
            System.out.printf("Operazione eseguita con successo!\n");
        }
        catch (SQLException s) 
        {
            System.out.println("Errore\n" + s);
        }
        
    }
    
    
    
    
    
    public static void Operazione6(Statement st, ResultSet rs) throws Exception
    {
        try
        {
            String sql = "SELECT Nome, Cognome, NumProdottiAcquistati FROM Cliente";
            rs = st.executeQuery(sql);
            System.out.printf("Nome  Cognome  NumProdotti\n");
            while (rs.next())  
            {
                String Nome = rs.getString("Nome");
                String Cognome = rs.getString("Cognome");
                int NumProdottiAcquistati = rs.getInt("NumProdottiAcquistati");
                System.out.printf("%s %s %d\n",Nome, Cognome, NumProdottiAcquistati);
            }
        }
        catch (SQLException s) 
        {
            System.out.println("Errore\n" + s);
        }
         
    }
    
    
    
    
    public static void Operazione7(Statement st, ResultSet rs) throws Exception
    {
        try
        {
            String sql = "SELECT Nome, Disponibilità FROM Prodotto WHERE Disponibilità > '0'";
            rs = st.executeQuery(sql);
            System.out.printf("Nome  Disponibilità\n");
            while (rs.next())  
            {
                String Nome = rs.getString("Nome");
                int Disponibilità = rs.getInt("Disponibilità");
                System.out.printf("%s %d\n",Nome, Disponibilità);
            }
        }
        catch (SQLException s) 
        {
            System.out.println("Errore\n" + s);
        }
         
    }
    
    
    
    
    public static void Operazione8(Statement st, ResultSet rs) throws Exception
    {
        try
        {
            String sql = "SELECT H.Tipologia, COUNT(*) AS NumProdotti FROM HARDWARE AS H, Prodotto AS P WHERE P.Disponibilità > '0' AND H.CodSeriale = P.CodSeriale GROUP BY H.Tipologia";
            rs = st.executeQuery(sql);
            System.out.printf("Tipologia  NumProdotti\n");
            while (rs.next())  
            {
                String Tipologia = rs.getString("Tipologia");
                int NumProdotti = rs.getInt("NumProdotti");
                System.out.printf("%s %d\n",Tipologia, NumProdotti);
            }
        }
        catch (SQLException s) 
        {
            System.out.println("Errore\n" + s);
        }
         
    }
    
    
    
    
    public static void Operazione9(Connection con, Statement st, ResultSet rs) throws Exception
    {
        try
        {            
            BufferedReader Read = new BufferedReader(new InputStreamReader(System.in));
            int i=0;
            String CD=null;
            String CodDipendente[] = new String[255];
            String sql = "SELECT CodDipendente FROM Dipendente";
            rs = st.executeQuery(sql);
            while (rs.next())  
            {
                CodDipendente[i] = rs.getString("CodDipendente");
                i++;
            }
            i=0;
            System.out.printf("Per eseguire questa operazione mi serve il tuo CodDipendente, inseriscilo:");
            while(CD == null)
            {
                CD = Read.readLine();              
                while(!(CD.equals(CodDipendente[i])))
                {
                    i++;
                    if (CodDipendente[i]==null) break;
                }
                if(!(CD.equals(CodDipendente[i])))
                {
                    i=0;
                    CD=null;
                    System.out.printf("CodDipendente non presente. Riprova: ");
                }                
            }
            
            
            String CodFornitore, NomeF, Via, Città, Telefono, CivicoS;
            int Civico;
            System.out.printf("Dammi il CodFornitore del nuovo fornitore: ");
            do
            {                
                CodFornitore = Read.readLine();
                if (CodFornitore.length()>20)
                {
                    System.out.printf("CodFornitore troppo lungo (max 20 caratteri), riprova: ");
                }
                else break;
            }while(true);
            System.out.printf("Dammi il Nome del nuovo fornitore: ");
            do
            {                
                NomeF = Read.readLine();
                if (NomeF.length()>50)
                {
                    System.out.printf("Nome troppo lungo (max 50 caratteri), riprova: ");
                }
                else break;
            }while(true); 
            System.out.printf("Dammi la Via del nuovo fornitore: ");
            do
            {                
                Via = Read.readLine();
                if (Via.length()>20)
                {
                    System.out.printf("Via troppo lunga (max 20 caratteri), riprova: ");
                }
                else break;
            }while(true);
            System.out.printf("Dammi il Civico del nuovo fornitore: ");
            CivicoS = Read.readLine();
            Civico = Integer.parseInt(CivicoS);
            System.out.printf("Dammi la Città del nuovo fornitore: ");
            do
            {                
                Città = Read.readLine();
                if (Città.length()>50)
                {
                    System.out.printf("Città troppo lungo (max 50 caratteri), riprova: ");
                }
                else break;
            }while(true);
            System.out.printf("Dammi il numero di telefono del nuovo fornitore compreso il prefisso: ");
            do
            {                
                Telefono = Read.readLine();
                if (Telefono.length()<13 || Telefono.length()>20)
                {
                    System.out.printf("Numero di Telefono sbagliato, riprova: ");
                }
                else break;
            }while(true);
            sql = "INSERT INTO Fornitore(CodFornitore, Nome, Via, Civico, Città, Telefono) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, CodFornitore);
            ps.setString(2, NomeF);
            ps.setString(3, Via);
            ps.setInt(4, Civico);
            ps.setString(5, Città);
            ps.setString(6, Telefono);
            int rs1 = ps.executeUpdate();
            
            
            String CodSeriale, NomeP, DisponibilitàS, PrezzoS, DataUscitaS, DataUscita;
            int Disponibilità;
            Float Prezzo;
            System.out.printf("Dammi il codSeriale del nuovo prodotto: ");
            do
            {                
                CodSeriale = Read.readLine();
                if (CodSeriale.length()>20)
                {
                    System.out.printf("CodSeriale troppo lungo (max 20 caratteri), riprova: ");
                }
                else break;
            }while(true); 
            System.out.printf("Dammi il Nome del nuovo prodotto: ");
            do
            {                
                NomeP = Read.readLine();
                if (NomeP.length()>50)
                {
                    System.out.printf("Nome troppo lungo (max 50 caratteri), riprova: ");
                }
                else break;
            }while(true); 
            System.out.printf("Dimmi la quantità in cifre che ti è stata rifornita del nuovo prodotto: ");
            DisponibilitàS = Read.readLine();
            Disponibilità = Integer.parseInt(DisponibilitàS);
            System.out.printf("Dammi il Prezzo del nuovo prodotto: ");
            PrezzoS = Read.readLine();
            Prezzo = Float.parseFloat(PrezzoS);
            System.out.printf("Dammi la Data di Uscita del nuovo prodotto nel formato 'AAAA-MM-GG': ");
            do
            {
                do
                {  
                    DataUscitaS = Read.readLine();
                    if(DataUscitaS.length()<10 || DataUscitaS.length()>10)
                    {
                       System.out.printf("Il formato della data non è corretto riprova: "); 
                    }
                    else break;
                }while(true);
                int anno = Integer.parseInt(DataUscitaS.substring(0, 4));
                int mese = Integer.parseInt(DataUscitaS.substring(5, 7));
                int giorno = Integer.parseInt(DataUscitaS.substring(8, 10));
                GregorianCalendar cal = new GregorianCalendar (anno, mese-1, giorno);
                cal.setLenient (false);
                try
                {
                    if (cal.get(Calendar.DATE)!='0')
                    {
                        java.sql.Date d = new java.sql.Date(cal.getTimeInMillis());
                        DataUscita = d.toString();
                        break;
                    }
                }
                catch (Exception e) 
                {
                    System.out.printf("Il formato della data non è corretto riprova: ");
                }
            }while(true);        
            sql = "INSERT INTO Prodotto(CodSeriale, Nome, Disponibilità, Prezzo, DataUscita, CodFornitore) VALUES (?,?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, CodSeriale);
            ps.setString(2, NomeP);
            ps.setInt(3, Disponibilità);
            ps.setFloat(4, Prezzo);
            ps.setString(5, DataUscita);
            ps.setString(6, CodFornitore);
            int rs2 = ps.executeUpdate();
            
            
            String Tipologia, Edizione;
            System.out.printf("Dammi la tipologia del nuovo prodotto di tipo Hardware: ");
            do
            {                
                Tipologia = Read.readLine();
                if (Tipologia.length()>50)
                {
                    System.out.printf("Tipologia troppo lunga (max 50 caratteri), riprova: ");
                }
                else break;
            }while(true); 
            System.out.printf("Dammi l'edizione del nuovo prodotto di tipo Hardware: ");
            do
            {                
                Edizione = Read.readLine();
                if (Edizione.length()>50)
                {
                    System.out.printf("Edizione troppo lunga (max 50 caratteri), riprova: ");
                }
                else break;
            }while(true); 
            sql = "INSERT INTO Hardware(CodSeriale, Tipologia, Edizione) VALUES (?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, CodSeriale);
            ps.setString(2, Tipologia);
            ps.setString(3, Edizione);
            int rs3 = ps.executeUpdate();
            
            
            Calendar cal = Calendar.getInstance();
            java.sql.Date d = new java.sql.Date(cal.getTimeInMillis());
            String data = d.toString();
            sql = "INSERT INTO Modifica(DataModifica, CodDipendente, CodSeriale) VALUES (?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, data);
            ps.setString(2, CD);
            ps.setString(3, CodSeriale);
            int rs4 = ps.executeUpdate();
            con.commit();
            System.out.printf("Operazione eseguita con successo!\n");
        }
        catch (SQLException s) 
        {
            System.out.println("Errore\n" + s);
        }
         
    }   
    
    
    
    public static void Operazione10(Connection con, Statement st, ResultSet rs) throws Exception
    {
        try
        {
            BufferedReader Read = new BufferedReader(new InputStreamReader(System.in));
            int i=0;
            String CD=null;
            String CodDipendente[] = new String[255];
            String sql = "SELECT CodDipendente FROM Dipendente";
            rs = st.executeQuery(sql);
            while (rs.next())  
            {
                CodDipendente[i] = rs.getString("CodDipendente");
                i++;
            }
            i=0;
            System.out.printf("Per eseguire questa operazione mi serve il tuo CodDipendente, inseriscilo: ");
            while(CD == null)
            {
                CD = Read.readLine();              
                while(!(CD.equals(CodDipendente[i])))
                {
                    i++;
                    if (CodDipendente[i]==null) break;
                }
                if(!(CD.equals(CodDipendente[i])))
                {
                    i=0;
                    CD=null;
                    System.out.printf("CodDipendente non presente. Riprova: ");
                }                
            }
            
            
            i=0;
            String CodSeriale[] = new String[255];
            sql = "SELECT CodSeriale, Nome, Prezzo FROM Prodotto";
            rs = st.executeQuery(sql);
            while (rs.next())  
            {
                CodSeriale[i] = rs.getString("CodSeriale");
                String Nome = rs.getString("Nome");
                Float Prezzo = rs.getFloat("Prezzo");
                System.out.printf("%s %s %.2f\n",CodSeriale[i], Nome, Prezzo);
                i++;
            }
            i=0;
            System.out.printf("Inseriscimi il CodSeriale del prodotto a cui modificare il prezzo: ");
            String CS = null;
            while(CS == null)
            {
                CS = Read.readLine();              
                while(!(CS.equals(CodSeriale[i])))
                {
                    i++;
                    if (CodSeriale[i]==null) break;
                }
                if(!(CS.equals(CodSeriale[i])))
                {
                    i=0;
                    CS=null;
                    System.out.printf("Hai sbagliato a digitare. Riprova: ");
                }                
            }   
            System.out.printf("Dammi il nuovo prezzo del prodotto: ");
            String prezzoS = Read.readLine();
            Float prezzo = Float.parseFloat(prezzoS);
            sql = "UPDATE Prodotto SET Prezzo = ? WHERE CodSeriale = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setFloat(1, prezzo);
            ps.setString(2, CS);
            int rs1 = ps.executeUpdate();
            
            
            Calendar cal = Calendar.getInstance();
            java.sql.Date d = new java.sql.Date(cal.getTimeInMillis());
            String data = d.toString();
            sql = "INSERT INTO Modifica(DataModifica, CodDipendente, CodSeriale) VALUES (?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, data);
            ps.setString(2, CD);
            ps.setString(3, CS);
            int rs4 = ps.executeUpdate();
            con.commit();
            System.out.printf("Operazione eseguita con successo!\n");
        }
        catch (SQLException s) 
        {
            System.out.println("Errore\n" + s);
        }
         
    }
    
    
    
    public static void Operazione11(Connection con, Statement st, ResultSet rs) throws Exception
    {
        try
        {
            BufferedReader Read = new BufferedReader(new InputStreamReader(System.in));
            int i=0;
            String CD=null;
            String CodDipendente[] = new String[255];
            String sql = "SELECT CodDipendente FROM Dipendente";
            rs = st.executeQuery(sql);
            while (rs.next())  
            {
                CodDipendente[i] = rs.getString("CodDipendente");
                i++;
            }
            i=0;
            System.out.printf("Per eseguire questa operazione mi serve il tuo CodDipendente, inseriscilo: ");
            while(CD == null)
            {
                CD = Read.readLine();              
                while(!(CD.equals(CodDipendente[i])))
                {
                    i++;
                    if (CodDipendente[i]==null) break;
                }
                if(!(CD.equals(CodDipendente[i])))
                {
                    i=0;
                    CD=null;
                    System.out.printf("CodDipendente non presente. Riprova: ");
                }                
            }
            
            
            i=0;
            String CF=null;
            String CodiceFiscale[] = new String[255];
            sql = "SELECT C.CodiceFiscale, C.Nome, C.Cognome FROM Cliente AS C, TesseraFedeltà AS TF WHERE C.CodiceFiscale = TF.CodiceFiscale AND TF.PuntiOttenuti > 5000";
            rs = st.executeQuery(sql);
            if(rs!=null)
            {
                System.out.printf("I clienti che possono effettuare un acquisto sfruttando uno sconto sono: \n");
                while (rs.next())  
                {
                    CodiceFiscale[i] = rs.getString("CodiceFiscale");
                    String NomeC = rs.getString("Nome");
                    String CognomeC = rs.getString("Cognome");
                    System.out.printf("%s %s %s\n",CodiceFiscale[i], NomeC, CognomeC);
                    i++;
                }
                System.out.printf("Inserisci il Codice Fiscale del cliente che ha effettuato l'acquisto: ");
                i=0;
                while(CF == null)
                {
                    CF = Read.readLine();              
                    while(!(CF.equals(CodiceFiscale[i])))
                    {
                        i++;
                        if (CodiceFiscale[i]==null) break;
                    }
                    if(!(CF.equals(CodiceFiscale[i])))
                    {
                        i=0;
                        CF=null;
                        System.out.printf("CodiceFiscale non presente. Riprova: ");
                    }
                }
                sql = "SELECT PuntiOttenuti FROM TesseraFedeltà WHERE CodiceFiscale = ?";
                PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ps.setString(1, CF);
                rs = ps.executeQuery();
                rs.first(); 
                String PuntiOttenutiS = rs.getString("PuntiOttenuti");
                int PuntiOttenuti = Integer.parseInt(PuntiOttenutiS);
                int PercentualeSconto=0, PercentualeScontoSC=0;
                String PercentualeScontoS=null;
                if(PuntiOttenuti >= 5000 && PuntiOttenuti < 7500)
                {
                    System.out.printf("Il cliente può ottenere il 5 per cento di sconto che verrà applicato sull'acquisto");
                    PercentualeSconto = 5;
                    EffettuaAcquisto(con, st, rs, CF, PercentualeSconto, CD);
                }   
                else if(PuntiOttenuti >= 7500 && PuntiOttenuti < 10000)
                {
                    System.out.printf("Il cliente può ottenere il 5 o il 10 per cento di sconto quale bisogna applicare? inserisci(solo valore assoluto, es. '5'): ");
                    PercentualeScontoS = Read.readLine();
                    PercentualeScontoSC = Integer.parseInt(PercentualeScontoS);
                    switch(PercentualeScontoSC)
                    {
                        case 5: 
                            {
                                PercentualeSconto = 5;
                                EffettuaAcquisto(con, st, rs, CF, PercentualeSconto , CD);
                                break;
                            }
                        case 10: 
                            {
                                PercentualeSconto = 10;
                                EffettuaAcquisto(con, st, rs, CF, PercentualeSconto, CD);
                                break;
                            }
                        default: 
                            {
                                System.out.println("Scelta non presente");
                                break;
                            }
                            
                    }
                } 
                else if(PuntiOttenuti >= 10000)
                {
                    System.out.printf("Il cliente può ottenere il 5, il 10 o il 15 per cento di sconto quale bisogna applicare? inserisci (solo valore assoluto, es. '5'): ");
                    PercentualeScontoS = Read.readLine();
                    PercentualeScontoSC = Integer.parseInt(PercentualeScontoS);
                    switch(PercentualeScontoSC)
                    {
                        case 5: 
                            {
                                PercentualeSconto = 5;
                                EffettuaAcquisto(con, st, rs, CF, PercentualeSconto, CD);
                                break;
                            }
                        case 10: 
                            {
                                PercentualeSconto = 10;
                                EffettuaAcquisto(con, st, rs, CF, PercentualeSconto, CD);
                                break;
                            }
                        case 15: 
                            {
                                PercentualeSconto = 15;
                                EffettuaAcquisto(con, st, rs, CF, PercentualeSconto, CD);
                                break;
                            }
                        default: 
                            {
                                System.out.println("Scelta non presente");
                                break;
                            }

                     }
                }
                con.commit();
                System.out.printf("Operazione eseguita con successo!\n");
            }
            else 
            {
                System.out.printf("Nessun cliente ha abbastanza punti per ottenere lo sconto, di conseguenza l'operazione non è possibile\n");
            }
            
        }
        catch (SQLException s) 
        {
            System.out.println("Errore\n" + s);
        }
         
    }
    
    public static void EffettuaAcquisto(Connection con, Statement st, ResultSet rs, String CF, int PercentualeSconto, String CD) throws Exception
    {
        try
        {
            BufferedReader Read = new BufferedReader(new InputStreamReader(System.in));
            int i=0;
            String CodSeriale[] = new String[255];
            String sql = "SELECT CodSeriale, Nome FROM Prodotto WHERE Disponibilità > 0";
            rs = st.executeQuery(sql);
            System.out.printf("Questi sono i prodotti presenti in negozio: \n");
            while (rs.next())  
            {
                CodSeriale[i] = rs.getString("CodSeriale");
                String nomeP = rs.getString("Nome");
                System.out.printf("%s %s\n",CodSeriale[i], nomeP);
                i++;
            }
            i=0;
            System.out.printf("Inseriscimi il CodSeriale del prodotto che il cliente vuole acquistare: ");
            String CP = null;
            while(CP == null)
            {
                CP = Read.readLine();              
                while(!(CP.equals(CodSeriale[i])))
                {
                    i++;
                    if (CodSeriale[i]==null) break;
                }
                if(!(CP.equals(CodSeriale[i])))
                {
                    i=0;
                    CP=null;
                    System.out.printf("Hai sbagliato a digitare. Riprova: ");
                }                
            } 
            Calendar cal = Calendar.getInstance();
            java.sql.Date d = new java.sql.Date(cal.getTimeInMillis());
            String data = d.toString();
            sql = "INSERT INTO Acquisto(PercentualeSconto, DataAcquisto, CodiceFiscale, CodSeriale) VALUES (?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, PercentualeSconto);
            ps.setString(2, data);
            ps.setString(3, CF);
            ps.setString(4, CP);
            int rs2 = ps.executeUpdate();
            AggiornaTessera(con, st, rs, CF, PercentualeSconto, CP);
            AggiornaCliente(con, st, rs, CF);
            AggiornaProdotto(con, st, rs, CP, CD);
            
        }
        catch (SQLException s) 
        {
            System.out.println("Errore\n" + s);
        }
    }
                
            
    public static void AggiornaTessera(Connection con, Statement st, ResultSet rs, String CF, int PercentualeSconto, String CP) throws Exception
    {
        try
        {
            String sql = "SELECT Prezzo FROM Prodotto WHERE CodSeriale = ?";
            PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ps.setString(1, CP);
            rs = ps.executeQuery();
            rs.first();  
            Float PrezzoF = rs.getFloat("Prezzo");
            Float PrezzoSpesoF = PrezzoF - PrezzoF/100*PercentualeSconto;
            int PrezzoSpeso =Math.round(PrezzoSpesoF);
            int NewPunti = PrezzoSpeso*10;
            
            sql = "SELECT PuntiOttenuti FROM TesseraFedeltà WHERE CodiceFiscale = ?";
            ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ps.setString(1, CF);
            rs = ps.executeQuery();
            rs.first();  
            int PuntiOttenuti = rs.getInt("PuntiOttenuti");
            
            int PuntiAggiornati=0;;
            switch(PercentualeSconto)
            {
                case 5: 
                    {
                        PuntiAggiornati = PuntiOttenuti + NewPunti - 5000;
                        break;
                    }
                case 10: 
                    {
                        PuntiAggiornati = PuntiOttenuti + NewPunti - 7500;
                        break;
                    }
                case 15: 
                    {
                        PuntiAggiornati = PuntiOttenuti + NewPunti - 10000;
                        break;
                    }
                default: 
                    {
                        System.out.println("Errore");
                        break;
                    }

             }  
            
            sql = "UPDATE TesseraFedeltà SET PuntiOttenuti = ? WHERE CodiceFiscale = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, PuntiAggiornati);
            ps.setString(2, CF);
            int rs1 = ps.executeUpdate();

            
        }
        catch (SQLException s) 
        {
            System.out.println("Errore\n" + s);
        }
    }
    
    public static void AggiornaCliente(Connection con, Statement st, ResultSet rs, String CF) throws Exception
    {
        try
        {   
            String sql = "UPDATE Cliente SET NumProdottiAcquistati = NumProdottiAcquistati + 1 WHERE CodiceFiscale = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, CF);
            int rs1 = ps.executeUpdate();

            
        }
        catch (SQLException s) 
        {
            System.out.println("Errore\n" + s);
        }
    }
    
    public static void AggiornaProdotto(Connection con, Statement st, ResultSet rs, String CP, String CD) throws Exception
    {
        try
        {
            String sql = "UPDATE Prodotto SET Disponibilità = Disponibilità - 1 WHERE CodSeriale = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, CP);
            int rs3 = ps.executeUpdate();
            

            Calendar cal = Calendar.getInstance();
            java.sql.Date d = new java.sql.Date(cal.getTimeInMillis());
            String data = d.toString();
            sql = "INSERT INTO Modifica(DataModifica, CodDipendente, CodSeriale) VALUES (?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, data);
            ps.setString(2, CD);
            ps.setString(3, CP);
            int rs4 = ps.executeUpdate();
        }   
        catch (SQLException s) 
        {
            System.out.println("Errore\n" + s);
        } 
    }
    
    
    
    public static void Operazione12(Connection con, Statement st, ResultSet rs)
    {
        try
        {
            String sql = "DELETE FROM Prodotto WHERE Disponibilità = 0";
            int result = st.executeUpdate(sql);
            con.commit();
            System.out.printf("Operazione eseguita con successo\n");
        }   
        catch (SQLException s) 
        {
            System.out.println("Errore\n" + s);
        } 
    }
    
    
    
    public static void Operazione13(Statement st, ResultSet rs) throws Exception
    {
        try
        {
            String sql = "SELECT C.CodiceFiscale, C.Nome, C.Cognome, TF.PuntiOttenuti FROM TesseraFedeltà AS TF, Cliente AS C WHERE C.CodiceFiscale = TF.CodiceFiscale AND TF.PuntiOttenuti IN (SELECT MAX(PuntiOttenuti) FROM TesseraFedeltà)";
            rs = st.executeQuery(sql);
            System.out.printf("Codice Fiscale   Nome   Cognome   Punti Ottenuti\n");
            while (rs.next())  
            {
                String CodiceFiscale = rs.getString("CodiceFiscale");
                String Nome = rs.getString("Nome");
                String Cognome = rs.getString("Cognome");
                int PuntiOttenuti = rs.getInt("PuntiOttenuti");
                System.out.printf("%s %s %s %d\n",CodiceFiscale, Nome, Cognome, PuntiOttenuti);
            }
        }
        catch (SQLException s) 
        {
            System.out.println("Errore\n" + s);
        }
         
    }
    
    
    
    
    public static void Operazione14(Connection con, Statement st, ResultSet rs) throws Exception
    {
        try
        {
            BufferedReader Read = new BufferedReader(new InputStreamReader(System.in));
            int i=0;
            String Nome[] = new String[255];
            String sql = "SELECT Nome FROM Prodotto";
            rs = st.executeQuery(sql);
            System.out.printf("Questi sono i nomi dei prodotti: \n");
            while (rs.next())  
            {
                Nome[i] = rs.getString("Nome");
                System.out.printf("%s\n",Nome[i]);
                i++;
            }
            i=0;
            System.out.printf("Inseriscimi il nome del prodotto che i clienti devono aver acquistato: ");
            String NP = null;
            while(NP == null)
            {
                NP = Read.readLine();              
                while(!(NP.equals(Nome[i])))
                {
                    i++;
                    if (Nome[i]==null) break;
                }
                if(!(NP.equals(Nome[i])))
                {
                    i=0;
                    NP=null;
                    System.out.printf("Hai sbagliato a digitare. Riprova: ");
                }                
            }
            
            i=0;
            System.out.printf("Ora inseriscimi il nome del prodotto che i clienti non devono aver acquistato: ");
            String NP1 = null;
            while(NP1 == null)
            {
                NP1 = Read.readLine();              
                while(!(NP1.equals(Nome[i])))
                {
                    i++;
                    if (Nome[i]==null) break;
                }
                if(!(NP1.equals(Nome[i])))
                {
                    i=0;
                    NP1=null;
                    System.out.printf("Hai sbagliato a digitare. Riprova: ");
                }                
            }
            sql = "SELECT C.Nome, C.Cognome FROM Cliente AS C, Acquisto AS A, Prodotto AS P WHERE P.Nome = ? AND P.CodSeriale = A.CodSeriale AND A.CodiceFiscale = C.CodiceFiscale AND NOT EXISTS (SELECT * FROM Acquisto AS A1, Prodotto AS P1 WHERE P1.Nome = ? AND P1.CodSeriale = A1.CodSeriale AND A1.CodiceFiscale = C.CodiceFiscale)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, NP);
            ps.setString(2, NP1);
            rs = ps.executeQuery();
            System.out.printf("Nome  Cognome\n");
            while (rs.next())  
            {
                String NomeF = rs.getString("Nome");
                String CognomeF = rs.getString("Cognome");
                System.out.printf("%s %s\n",NomeF, CognomeF);
            }
        }
        catch (SQLException s) 
        {
            System.out.println("Errore\n" + s);
        }
         
    }
    
    
    
    public static void Operazione15(Statement st, ResultSet rs) throws Exception
    {
        try
        {
            String sql = "SELECT C.Nome, C.Cognome, SUM(P.Prezzo-(P.Prezzo/100*A.PercentualeSconto)) AS SpesaTotale FROM Cliente AS C, Acquisto AS A, Prodotto AS P WHERE P.CodSeriale = A.CodSeriale AND A.CodiceFiscale = C.CodiceFiscale GROUP BY C.CodiceFiscale";
            rs = st.executeQuery(sql);
            System.out.printf("Nome  Cognome  SpesaTotale\n");
            while (rs.next())  
            {
                String Nome = rs.getString("Nome");
                String Cognome = rs.getString("Cognome");
                Float SpesaTotale = rs.getFloat("SpesaTotale");
                System.out.printf("%s %s %.2f\n",Nome, Cognome, SpesaTotale);
            }
        }
        catch (SQLException s) 
        {
            System.out.println("Errore\n" + s);
        }
         
    }
    
    
    public static void Operazione16(Connection con, Statement st, ResultSet rs) throws Exception
    {
        try
        {
            BufferedReader Read = new BufferedReader(new InputStreamReader(System.in));
            int i=0;
            String NomePiattaforma[] = new String[255];
            String sql = "SELECT Nome FROM PiattaForma";
            rs = st.executeQuery(sql);
            System.out.printf("Questi sono le piattaforme: \n");
            while (rs.next())  
            {
                NomePiattaforma[i] = rs.getString("Nome");
                System.out.printf("%s\n",NomePiattaforma[i]);
                i++;
            }
            i=0;
            System.out.printf("Inseriscimi il nome della piattaforma di cui vuoi vedere i videogiochi disponibili: ");
            String NPF = null;
            while(NPF == null)
            {
                NPF = Read.readLine();              
                while(!(NPF.equals(NomePiattaforma[i])))
                {
                    i++;
                    if (NomePiattaforma[i]==null) break;
                }
                if(!(NPF.equals(NomePiattaforma[i])))
                {
                    i=0;
                    NPF=null;
                    System.out.printf("Hai sbagliato a digitare. Riprova: ");
                }                
            }
            sql = "SELECT P.Nome FROM Prodotto AS P, Videogioco AS V, DisponibilePer AS DP, Piattaforma AS PA  WHERE P.CodSeriale = V.CodSeriale AND V.CodSeriale = DP.CodSeriale AND DP.Nome = PA.Nome AND PA.Nome = ? AND P.Disponibilità > 0";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, NPF);
            rs = ps.executeQuery();
            System.out.printf("I videogiochi disponibili per la piattaforma %s sono: \n", NPF);
            while (rs.next())  
            {
                String Nome = rs.getString("Nome");
                System.out.printf("%s\n",Nome);
            }
        }
        catch (SQLException s) 
        {
            System.out.println("Errore\n" + s);
        }
         
    }
} 

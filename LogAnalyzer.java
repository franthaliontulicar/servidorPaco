/**
 * Read web server data and analyse
 * hourly access patterns.
 * 
 * @author David J. Barnes and Michael KÃ¶lling.
 * @version 2011.07.31
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer()
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader();
    }
    
    public LogAnalyzer(String nombreLog){
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader(nombreLog);
    
    }
            
    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        int hour = 0;
        while( hour < hourCounts.length) {
            System.out.println(hour + ": " + hourCounts[hour]);
        }
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
    
    
     public int numberOfAccesses()
    {
        int numeroDeAccesos = 0;
        int index = 0;
        while(index < hourCounts.length)
        {
            numeroDeAccesos =numeroDeAccesos + hourCounts[index];
            index++;
        }
        return numeroDeAccesos;  
  
    }
  
  	/**
     * Devuelve a qué hora el servidor tuvo que responder a más peticiones.
     * Si hay empate devuelve la última de las horas. Si no ha habido acceso,
     * informa del hecho por pantalla y devuelve -1
     */
    public int busiestHour(){
        int masAcceso = -1;
        int hora = 0;
        while(hora < hourCounts.length) {
            if(masAcceso != -1){
                if(hourCounts[hora] >= hourCounts[masAcceso]){
                   masAcceso = hora;
                }
            }
            else if(hourCounts[hora] != 0){masAcceso = hora;}
           hora++;
        }
        if(masAcceso == -1){System.out.println("No ha habido accesos.");}
        return masAcceso;
    }
    
    public int horaMenosCarga(){
        int bajoAcceso = -1;
        int hora = 0;
        while(hora < hourCounts.length) {
            if(bajoAcceso != -1){
                if(hourCounts[hora] <= hourCounts[bajoAcceso]){
                    bajoAcceso = hora;
                }
            }
            else if(hourCounts[hora] != 0){bajoAcceso = hora;}
            hora++;
        }
        if(bajoAcceso == -1){System.out.println("No ha habido accesos.");}
        return bajoAcceso;
    }
    
    public int dosHorasMasCarga(){
        int masAcceso = -1;
        int numAcceso = 0;
        int hora = 0;
        while(hora < hourCounts.length) {
            if(numAcceso != 0){
                if((hourCounts[hora] + hourCounts[hora - 1]) >= numAcceso){
                    numAcceso = hourCounts[hora] + hourCounts[hora - 1];
                    masAcceso = hora ;
                }
            }
            
            hora++;
        }
        if(numAcceso == 0){System.out.println("No ha habido accesos.");}
        return masAcceso;
    }
    
   
}

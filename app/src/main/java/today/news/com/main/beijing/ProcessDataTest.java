package today.news.com.main.beijing;

public class ProcessDataTest {

    private static ProcessDataTest mInstance;
    private String processDec;



    public ProcessDataTest() {
    }


    public static synchronized ProcessDataTest getInstance(){
        if (mInstance == null){
            mInstance = new ProcessDataTest();
        }
        return mInstance;
    }

    public String getProcessDec() {
        return processDec;
    }

    public ProcessDataTest setProcessDec(String processDec) {
        this.processDec = processDec;
        return this;
    }
}

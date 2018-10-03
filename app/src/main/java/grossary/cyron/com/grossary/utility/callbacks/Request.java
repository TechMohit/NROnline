package grossary.cyron.com.grossary.utility.callbacks;



public abstract class Request {

    public abstract void enqueue();

    public abstract void cancel();

    public abstract void retry();

    public boolean canLoadMore() {
        return false;
    }

    public String getNextUri() {
        return "";
    }

}

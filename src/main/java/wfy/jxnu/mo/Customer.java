package wfy.jxnu.mo;

public class Customer {
    private int custId;  //顾客编号，系统内部
    private  String             custNo;//用户名
    private  String custPWD;//密码

    public String getCustImg() {
        return custImg;
    }

    public void setCustImg(String custImg) {
        this.custImg = custImg;
    }

    private  String  custImg; //头像


    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    public String getCustPWD() {
        return custPWD;
    }

    public void setCustPWD(String custPWD) {
        this.custPWD = custPWD;
    }


    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }
}

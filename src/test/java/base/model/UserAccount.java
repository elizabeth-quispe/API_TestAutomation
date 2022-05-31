package base.model;

import java.util.List;

public class UserAccount {

    private List<ClientTransaction> clientTransactions;

    public List<ClientTransaction> getClientTransactions() {
        return clientTransactions;
    }

    public void setClientTransactions(List<ClientTransaction> clientTransactions) {
        this.clientTransactions = clientTransactions;
    }

}

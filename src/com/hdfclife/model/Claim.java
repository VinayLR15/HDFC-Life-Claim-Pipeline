package com.hdfclife.model;

public class Claim {
    private final String claimId;
    private final int amount;
    private final String policyNumber;
    private final String customerName;
    private final Urgency urgency;

    public Claim(String claimId, int amount, String policyNumber, String customerName, Urgency urgency) {
        this.claimId = claimId;
        this.amount = amount;
        this.policyNumber = policyNumber;
        this.customerName = customerName;
        this.urgency = urgency;
    }

    public String getClaimId() {
        return claimId;
    }
    public int getAmount() {
        return amount;
    }
    public String getPolicyNumber() {
        return policyNumber;
    }
    public String getCustomerName() {
        return customerName;
    }
    public Urgency getUrgency() {
        return urgency;
    }

    @Override
    public String toString() {
        return "Claim{" +
                "claimId='" + claimId + '\'' +
                ", amount=" + amount +
                ", policyNumber='" + policyNumber + '\'' +
                ", customerName='" + customerName + '\'' +
                ", urgency=" + urgency +
                '}';
    }
}

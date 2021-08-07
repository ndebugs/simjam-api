package com.ndebugs.simjam.messaging;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionMessage {

    private Integer id;
    private Integer memberId;
    private TransactionType type;
    private BigDecimal amount;
    private LocalDateTime timestamp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "TransactionMessage{" + "id=" + id + ", memberId=" + memberId + ", type=" + type + ", amount=" + amount + ", timestamp=" + timestamp + '}';
    }
}

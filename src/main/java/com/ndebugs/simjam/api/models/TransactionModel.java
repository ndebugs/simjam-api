package com.ndebugs.simjam.api.models;

import com.ndebugs.simjam.api.entities.TransactionType;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;

public class TransactionModel {

    private Integer id;

    @NotNull
    private Integer memberId;

    @NotNull
    private TransactionType type;

    @NotNull
    private BigDecimal amount;
    
    @NotNull
    private LocalDateTime time;

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

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "TransactionModel{" + "id=" + id + ", memberId=" + memberId + ", type=" + type + ", amount=" + amount + ", time=" + time + '}';
    }
}

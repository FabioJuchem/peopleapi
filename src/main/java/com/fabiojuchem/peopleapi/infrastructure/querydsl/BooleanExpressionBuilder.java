package com.fabiojuchem.peopleapi.infrastructure.querydsl;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public class BooleanExpressionBuilder {
    private List<BooleanExpression> expressions = new ArrayList<>();

    private BooleanExpressionBuilder(Supplier<BooleanExpression> supplier) {
        this.expressions.add(supplier.get());
    }
    private BooleanExpressionBuilder() {
    }
    public static BooleanExpressionBuilder builder() {
        return new BooleanExpressionBuilder();
    }

    public static BooleanExpressionBuilder builder(Supplier<BooleanExpression> supplier) {
        return new BooleanExpressionBuilder(supplier);
    }

    public BooleanExpressionBuilder expression(Object value, Supplier<BooleanExpression> supplier) {
        if (Objects.nonNull(value)) {
            this.expressions.add(supplier.get());
        }
        return this;
    }

    public BooleanExpressionBuilder expression(boolean expression, Supplier<BooleanExpression> supplier) {
        if (expression) {
            this.expressions.add(supplier.get());
        }
        return this;
    }

    public BooleanExpressionBuilder expression(BooleanExpression expression) {
        this.expressions.add(expression);
        return this;
    }

    public BooleanExpression build() {
        return expressions.stream().reduce(BooleanExpression::and).orElse(null);
    }
}

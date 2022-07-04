package com.mmenshikov.lyukinafashion.order.listener;

import com.mmenshikov.lyukinafashion.domain.entity.ProductOrder;
import com.mmenshikov.lyukinafashion.interfaces.NotificationService;
import com.mmenshikov.lyukinafashion.order.configuration.AdminProperties;
import com.mmenshikov.lyukinafashion.order.event.OrderSavedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class EventListener {

    private final NotificationService notificationService;
    private final AdminProperties adminProperties;

    @org.springframework.context.event.EventListener
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    public void onOrderSavedEvent(OrderSavedEvent event) {
        var orders = event.getProductOrders();
        sendEmail(orders);
    }

    private void sendEmail(final List<ProductOrder> order) {
        try {
            var orderComposition = buildOrderComposition(order);
            var orderInfo = order.get(0).getOrderInfo();
            var body = String.format("<h1>Новый заказ!</h1> <br/> <br/> <h2>Имя \"%s\", телефон %s, адрес \"%s\", email %s, контактировать через %s.</h2>" +
                            "<p><br/>Состав заказа :" +
                            "<ul>%s</ul></p>",
                    orderInfo.getContactName(),
                    orderInfo.getPhone(),
                    orderInfo.getAddress(),
                    StringUtils.hasText(orderInfo.getEmail()) ? "\"" + orderInfo.getEmail() + "\"" : " не указан",
                    orderInfo.getContactType().getValue(),
                    orderComposition);
            notificationService.send(adminProperties.getEmail(), body, "Новый заказ!");
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    private String buildOrderComposition(List<ProductOrder> productOrders) {
        return productOrders
                .stream()
                .map(productOrder -> String.format("<li>\"%s\", Размер: %s, Количество: %s</li>", productOrder.getProductSize().getProduct().getName(),
                        productOrder.getProductSize().getSize().getName(), productOrder.getQuantity()))
                .collect(Collectors.joining("\n"));
    }
}

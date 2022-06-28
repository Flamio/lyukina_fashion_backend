package com.mmenshikov.lyukinafashion.order.listener;

import com.mmenshikov.lyukinafashion.domain.entity.ProductOrder;
import com.mmenshikov.lyukinafashion.interfaces.NotificationService;
import com.mmenshikov.lyukinafashion.interfaces.ProductService;
import com.mmenshikov.lyukinafashion.order.event.OrderSavedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
@Slf4j
public class EventListener {

    private final NotificationService notificationService;
    private final ProductService productService;


    @org.springframework.context.event.EventListener
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onOrderSavedEvent(OrderSavedEvent event) {
        var order = event.getProductOrder();
        sendEmail(order);
    }

    private void sendEmail(final ProductOrder order) {
        try {
            var product = productService.get(order.getProductId());
            var body = String.format("Новый заказ. Телефон %s, адрес %s, изделие %s",
                    order.getOrder().getPhone(),
                    order.getOrder().getAddress(),
                    product.getName());
            notificationService.send("anna_lyukina@mail.ru", body, "Новый заказ!");
        }
        catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }
}

package com.example.prm392project.presenter;

import com.example.prm392project.model.PaymentModel;
import com.example.prm392project.view.PaymentQRCodeView;

public class PaymentPresenter {
    private PaymentQRCodeView view;

    public PaymentPresenter(PaymentQRCodeView view) {
        this.view = view;
    }

    public void generatePaymentDetails(String selectedPackage) {
        // Tạo thông tin thanh toán
        String amount = selectedPackage.split(": ")[1]; // Lấy giá trị tiền từ gói đã chọn
        PaymentModel payment = new PaymentModel(
                "VCB",
                "11665751",
                "NGUYEN XUAN VIET",
                amount,
                "Nâng cấp tài khoản premium"
        );
        view.displayPaymentDetails(payment);
    }
}

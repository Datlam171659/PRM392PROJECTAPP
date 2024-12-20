package com.example.prm392project.view;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.prm392project.R;
import com.example.prm392project.model.PaymentModel;
import com.example.prm392project.presenter.PaymentPresenter;

public class PaymentQRCodeActivity extends AppCompatActivity implements PaymentQRCodeView {
    private PaymentPresenter presenter;
    private TextView qrInstruction, bankInfo, paymentDetails, countdownTimer;
    private ImageView qrCodeImage;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_qr_layout);

        qrInstruction = findViewById(R.id.qrInstruction);
        bankInfo = findViewById(R.id.bankInfo);
        paymentDetails = findViewById(R.id.paymentDetails);
        countdownTimer = findViewById(R.id.countdownTimer);
        qrCodeImage = findViewById(R.id.qrCodeImage);
        backButton = findViewById(R.id.backButton);
        presenter = new PaymentPresenter(this);

        String selectedPackage = getIntent().getStringExtra("SELECTED_PACKAGE");
        presenter.generatePaymentDetails(selectedPackage);

        startCountdownTimer();

        backButton.setOnClickListener(view -> finish());

        qrCodeImage.setImageResource(R.drawable.qr_bank);
    }

    private void startCountdownTimer() {
        new CountDownTimer(600000, 1000) { // 30 phút
            public void onTick(long millisUntilFinished) {
                long minutes = (millisUntilFinished / 1000) / 60;
                long seconds = (millisUntilFinished / 1000) % 60;
                countdownTimer.setText("Mã thanh toán hết hạn sau: " + minutes + " phút " + seconds + " giây");
            }

            public void onFinish() {
                countdownTimer.setText("Mã thanh toán đã hết hạn");
                backButton.setEnabled(false); // Khóa nút quay lại
            }
        }.start();
    }

    @Override
    public void displayPaymentDetails(PaymentModel payment) {
        bankInfo.setText("Ngân hàng: " + payment.getBankName() +
                "\nSố tài khoản: " + payment.getAccountNumber() +
                "\nChủ tài khoản: " + payment.getAccountHolder());
        paymentDetails.setText("Số tiền: " + payment.getAmount() + "\nNội dung chuyển tiền: " + payment.getContent());
    }
}

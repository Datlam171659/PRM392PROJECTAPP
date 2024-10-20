package com.example.prm392project.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.prm392project.R;
import com.example.prm392project.model.PackageModel;
import com.example.prm392project.presenter.UpdateAccountPresenter;

import java.util.List;

public class UpdateAccountActivity extends AppCompatActivity implements UpdateAccountView {

    private UpdateAccountPresenter presenter;
    private LinearLayout packageContainer;
    private String selectedPackage; // Biến lưu gói đã chọn

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upgrade_account_layout);

        packageContainer = findViewById(R.id.packageContainer);
        Button createPaymentCodeButton = findViewById(R.id.createPaymentCodeButton);
        presenter = new UpdateAccountPresenter(this);

        // Hiển thị danh sách gói
        displayPackages(presenter.getPackages());

        createPaymentCodeButton.setOnClickListener(view -> {
            if (selectedPackage != null) {
                presenter.createPaymentCode(selectedPackage);
            } else {
                Toast.makeText(this, "Vui lòng chọn một gói trước khi thanh toán.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void displayPackages(List<PackageModel> packages) {
        packageContainer.removeAllViews(); // Xóa các gói cũ trước khi thêm mới
        for (PackageModel packageModel : packages) {
            LinearLayout packageLayout = new LinearLayout(this);
            packageLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            packageLayout.setBackgroundResource(R.drawable.package_background); // Gán nền cho gói
            packageLayout.setPadding(16, 16, 16, 16);
            packageLayout.setGravity(Gravity.CENTER);
            packageLayout.setOrientation(LinearLayout.VERTICAL);

            TextView packageView = new TextView(this);
            packageView.setText(packageModel.getPackageName());
            packageView.setTextSize(18);
            packageView.setTextColor(getResources().getColor(R.color.black)); // Sử dụng màu từ tài nguyên

            // Cài đặt sự kiện click
            packageLayout.setOnClickListener(view -> {
                selectedPackage = packageModel.getPackageName(); // Gán gói đã chọn
                Toast.makeText(this, "Đã chọn: " + selectedPackage, Toast.LENGTH_SHORT).show();
            });

            packageLayout.addView(packageView); // Thêm TextView vào LinearLayout
            packageContainer.addView(packageLayout); // Thêm LinearLayout vào packageContainer
        }
    }

    @Override
    public void navigateToPaymentQRCode(String selectedPackage) {
        Intent intent = new Intent(this, PaymentQRCodeActivity.class);
        intent.putExtra("SELECTED_PACKAGE", selectedPackage);
        startActivity(intent);
    }
}

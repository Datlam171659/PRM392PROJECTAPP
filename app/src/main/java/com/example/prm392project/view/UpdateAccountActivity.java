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
    private LinearLayout selectedPackageLayout; // Biến lưu layout của gói đã chọn

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
                resetSelectedPackage(); // Đặt lại màu cho gói khi bấm thanh toán
            } else {
                Toast.makeText(this, "Vui lòng chọn một gói trước khi thanh toán.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void displayPackages(List<PackageModel> packages) {
        packageContainer.removeAllViews(); // Xóa các gói cũ trước khi thêm mới
        for (PackageModel packageModel : packages) {
            // Tạo một LinearLayout cho mỗi gói
            LinearLayout packageLayout = new LinearLayout(this);
            packageLayout.setOrientation(LinearLayout.VERTICAL);
            packageLayout.setBackgroundResource(R.drawable.border_background); // Sử dụng drawable border
            packageLayout.setPadding(16, 16, 16, 16);
            packageLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));

            // Tạo một TextView cho tên gói
            TextView packageView = new TextView(this);
            packageView.setText(packageModel.getPackageName());
            packageView.setTextSize(23);
            packageView.setGravity(Gravity.CENTER);
            packageView.setTextColor(getResources().getColor(R.color.black));

            // Tạo một TextView cho mô tả gói
            TextView descriptionView = new TextView(this);
            descriptionView.setText(packageModel.getDescription());
            descriptionView.setTextSize(16);
            descriptionView.setGravity(Gravity.CENTER);
            descriptionView.setTextColor(getResources().getColor(R.color.gray));

            // Thêm tên gói và mô tả vào packageLayout
            packageLayout.addView(packageView);
            packageLayout.addView(descriptionView);

            // Thêm khoảng cách giữa các gói
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 150, 0, 30); // Thay đổi giá trị này để điều chỉnh khoảng cách giữa các gói
            packageLayout.setLayoutParams(params);

            // Cài đặt sự kiện click
            packageLayout.setOnClickListener(view -> {
                // Đặt lại màu cho gói đã chọn trước đó nếu có
                resetSelectedPackage();

                // Gán gói đã chọn
                selectedPackage = packageModel.getPackageName();
                selectedPackageLayout = packageLayout; // Lưu lại layout của gói đã chọn

                // Đổi màu nền cho gói đã chọn
                selectedPackageLayout.setBackgroundColor(getResources().getColor(R.color.green)); // Màu xanh lá cây
                Toast.makeText(this, "Đã chọn: " + selectedPackage, Toast.LENGTH_SHORT).show();
            });

            // Thêm packageLayout vào packageContainer
            packageContainer.addView(packageLayout);
        }
    }

    private void resetSelectedPackage() {
        if (selectedPackageLayout != null) {
            // Đặt lại màu nền về màu ban đầu
            selectedPackageLayout.setBackgroundResource(R.drawable.border_background); // Hoặc sử dụng màu mặc định
        }
        selectedPackage = null; // Đặt lại gói đã chọn
        selectedPackageLayout = null; // Đặt lại layout đã chọn
    }

    @Override
    public void navigateToPaymentQRCode(String selectedPackage) {
        Intent intent = new Intent(this, PaymentQRCodeActivity.class);
        intent.putExtra("SELECTED_PACKAGE", selectedPackage);
        startActivity(intent);
    }
}

package com.example.prm392project.presenter;

import com.example.prm392project.model.PackageModel;
import com.example.prm392project.view.UpdateAccountView;

import java.util.ArrayList;
import java.util.List;

public class UpdateAccountPresenter {
    private UpdateAccountView view;

    public UpdateAccountPresenter(UpdateAccountView view) {
        this.view = view;
    }

    public List<PackageModel> getPackages() {
        List<PackageModel> packages = new ArrayList<>();
        packages.add(new PackageModel("WeCare Bạc: 199.000 VND/tháng", "Tư vấn từ chuyên gia dinh dưỡng mỗi tháng, và các tính năng gói Bạc"));
        packages.add(new PackageModel("WeCare Vàng: 349.000 VND/tháng", "Tư vấn video call 2 lần/tháng, kiểm tra sức khỏe 6 tháng/lần, và lợi ích gói Vàng."));
        packages.add(new PackageModel("WeCare KC: 499.000 VND/tháng", "Hỗ trợ 24/7, kiểm tra sức khỏe 3 tháng/lần, tư vấn cá nhân hóa, và lợi ích gói KC."));
        return packages;
    }

    public void createPaymentCode(String selectedPackage) {
        view.navigateToPaymentQRCode(selectedPackage);
    }
}

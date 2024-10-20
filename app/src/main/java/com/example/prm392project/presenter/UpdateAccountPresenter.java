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
        packages.add(new PackageModel("Goi 1: 199.000 VNĐ", "199000"));
        packages.add(new PackageModel("Gói 2: 349.000 VNĐ", "349000"));
        packages.add(new PackageModel("Gói 3: 499.000 VNĐ", "499000"));
        return packages;
    }

    public void createPaymentCode(String selectedPackage) {
        view.navigateToPaymentQRCode(selectedPackage);
    }
}

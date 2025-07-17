package com.example.demo.controller; // ← パッケージ宣言（そのままでOK）

import com.example.demo.entity.Patient; // ← エンティティの読み込み
import com.example.demo.repository.PatientRepository; // ← リポジトリを使うため
import org.springframework.web.bind.annotation.*; // ← API用アノテーションをまとめてimport

import java.util.List; // ← List型のため

// ✅ JSONを返すコントローラであることを指定
@RestController

// ✅ このコントローラの共通URL部分を定義（例：/api/patients）
@RequestMapping("/api/patients")

// ✅ Reactのポート(3000)からのリクエストを許可
@CrossOrigin(origins = "http://localhost:3000")
public class PatientRestController {

    private final PatientRepository repository;

    // ✅ リポジトリを受け取るコンストラクタ（Springが自動で注入してくれる）
    public PatientRestController(PatientRepository repository) {
        this.repository = repository;
    }

    // ✅ GET /api/patients → 全件取得
    @GetMapping
    public List<Patient> getAllPatients() {
        return repository.findAll();
    }

    // ✅ GET /api/patients/search?name=〇〇 → 名前で検索
    @GetMapping("/search")
    public List<Patient> searchByName(@RequestParam String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }
}

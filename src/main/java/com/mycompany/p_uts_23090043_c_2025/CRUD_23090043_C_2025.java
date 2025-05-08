/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.p_uts_23090043_c_2025;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 *
 * @author BIMAZZNXT
 */
public class CRUD_23090043_C_2025 { 

    //membuat fungsi koneksi ke mongoDB
    public static MongoDatabase konekDB() {
        try {
            MongoClient client = MongoClients.create(); // menghubungkan ke client mongoDB
            MongoDatabase database = client.getDatabase("uts_23090043_C_2025"); // menghubungkan ke database
            System.out.println("Koneksi Sukses"); // menampilkan pesan koneksi sukses jika kode di atas telah berhasil ter-compile
            return database; // kembalikan value database
        } catch (Exception e) {
            System.out.println("Koneksi Gagal: " + e.getMessage()); // menampilkan pesan error jika database tidak dapat terhubung
        }

        return null;
    }
    
    public static void main(String[] args) {
        // Koneksi Ke Database
        MongoDatabase database = konekDB(); // membuat variabel untuk mengkoneksikan ke database
        
        // menambah data
        System.out.println("Menambahkan Data");
        MongoCollection<Document> col = database.getCollection("coll_23090043_C_2025"); // menghubungkan ke collection database
        Document doc = new Document(); // membuat dokumen/data baru dengan variabel doc
                        doc.put("nama", "Honda Brio"); // membuat dimensi data
                        doc.put("warna", "putih"); // membuat dimensi data
                        doc.put("harga", 180000000); // membuat dimensi data
                        col.insertOne(doc); // memasukan dimensi data (doc) ke dalam collection
                        
        Document doc2 = new Document();
                        doc2.put("nama", "Honda BRV");
                        doc2.put("warna", "putih");
                        doc2.put("harga", 380000000);
                        col.insertOne(doc2);
                        
        Document doc3 = new Document();
                        doc3.put("nama", "Honda BRV");
                        doc3.put("warna", "putih");
                        doc3.put("harga", 380000000);
                        col.insertOne(doc3);
        System.out.println("Data Tersimpan");
        
        // mendapatkan id dari data yang telah ditambahkan
        ObjectId id = new ObjectId(doc.get("_id").toString());
        
        //melihat semua data
        System.out.println("Data di koleksi: ");
        MongoCursor<Document> cursor = col.find().iterator(); // mencari data di collection 
        while (cursor.hasNext()) { 
            System.out.println(cursor.next().toJson()); // menampilkan data dengan iterasi sampai semua data di collection terbaca
        }
        
        //edit data
        Bson filter = Filters.eq("nama", "Honda Brio"); // mencari data berdasarkan nama
        Bson update = Updates.set("nama", "Honda WRV"); // update/set data baru
            col.updateOne(filter, update); //update data di collection
        
        //menghapus data
//      Bson del = Filters.eq("nama", "Honda Brio");

        //mencari dokumen berdasarkan nama 
        Bson fil = Filters.eq("nama", "Honda WRV"); //mencari data berdasarkan nama
        FindIterable<Document> search = col.find(fil); //mencari kolom berdasarkan filter sebelumnya
        for (Document docu : search) {
            System.out.println(docu.toJson()); // mencari data dengan iterasi
                }
    }
    
    
}

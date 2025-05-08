/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.p_uts_23090043_c_2025;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author BIMAZZNXT
 */
public class CRUD_23090043_C_2025 { 
    
    public static MongoDatabase konekDB() {
        try {
            MongoClient client = MongoClients.create();
            MongoDatabase database = client.getDatabase("uts_23090043_C_2025");
            System.out.println("Koneksi Sukses");
            return database;
        } catch (Exception e) {
            System.out.println("Koneksi Gagal: " + e.getMessage());
        }

        return null;
    }
    
    public static void main(String[] args) {
        // Koneksi Ke Database
        MongoDatabase database = konekDB(); // membuat variabel untuk mengkoneksikan ke database
        
        // menambah data
        System.out.println("Menambahkan Data");
        MongoCollection<Document> col = database.getCollection("coll_23090043_C_2025");
        Document doc = new Document();
                        doc.put("nama", "Honda Brio");
                        doc.put("warna", "putih");
                        doc.put("harga", 180000000);
                        col.insertOne(doc);
                        
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
        
        //melihat data
        System.out.println("Data di koleksi: ");
        MongoCursor<Document> cursor = col.find().iterator();
        while (cursor.hasNext()) {
            System.out.println(cursor.next().toJson());
        }
        
        //edit data
        Document document = new Document();
        document.put("nama", "Honda BRV");
        document.put("warna", "abu-abu");
        document.put("harga", 302000000);
        Document updated = new Document("$set", document);
        UpdateResult hasilEdit = col.updateOne(eq("_id",id),updated);
        System.out.println(hasilEdit.getModifiedCount());
        
        //menghapus data
//        col.deleteOne(eq("_id", id));

        //mencari dokumen berdasarkan id 
        Document Docu = col.find(eq("_id", id)).first();
        System.out.println("Pencarian data berdasarkan id: "+id);
        System.out.println(Docu.toJson());
        
    }
    
    
}

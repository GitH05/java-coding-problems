package com.dhawal.dao;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dhawal.db.MyConnection;
import com.dhawal.modal.Data;

public class DataDao {

    public static List<Data> getAllFiles(String email) throws SQLException {
        Connection con = MyConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM data WHERE email = ?");
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        List<Data> files = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt(1);
            String name = rs.getString(2);
            String path = rs.getString(3);

            Data data = new Data(id, name, path);
            files.add(data);
        }
        return files;
    }

    public static int hideFile(Data file) throws SQLException, IOException {
        Connection con = MyConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("INSERT INTO data(name, path, email, bin_data) VALUES(?, ?, ?, ?)");
        ps.setString(1, file.getFilenmae());
        ps.setString(2, file.getPath());
        ps.setString(3, file.getEmail());

        File f = new File(file.getPath());

        FileReader fr = new FileReader(f);

        ps.setCharacterStream(4, fr, f.length());

        int ans = ps.executeUpdate();
        fr.close();
        f.delete();
        return ans;
    }

    public static void unhide(int id) throws SQLException, IOException {
        Connection con = MyConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT path, bin_data FROM data WHERE id = ?");
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();

        String path = rs.getString("path");
        Clob c = rs.getClob("bin_data");

        Reader r = c.getCharacterStream();
        FileWriter fw = new FileWriter(path);

        int i;
        while ((i = r.read()) != -1) {
            fw.write((char) i);
        }
        fw.close();
        ps = con.prepareStatement("DELETE FROM data WHERE id = ?");

        ps.setInt(1, id);
        ps.executeUpdate();
        System.out.println("Unhide Successfully!");
    }
}

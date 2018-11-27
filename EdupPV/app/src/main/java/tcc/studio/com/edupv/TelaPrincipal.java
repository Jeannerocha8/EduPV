package tcc.studio.com.edupv;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class TelaPrincipal extends AppCompatActivity

        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Fragment[] fragment = {null};

        setContentView(R.layout.activity_tela_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //setando os botoes
        Button tutorial = (Button) findViewById(R.id.btnEstudo);
        Button multimidia = (Button) findViewById(R.id.btnVideo);
        Button atividades = (Button) findViewById(R.id.btnAtividade);
        Button sobrenos = (Button) findViewById(R.id.btnSobre);

        //click nos botoes
        tutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              try {
                  getSupportFragmentManager().
                          beginTransaction().
                          replace(R.id.telaBotton, new ListagemEstudo()).addToBackStack(null).
                          commit();

              }catch (Exception e){
                  e.printStackTrace();
              }
            }
        });

        multimidia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    getSupportFragmentManager().
                            beginTransaction().
                            replace(R.id.telaBotton, new ListagemVideo()).addToBackStack(null).
                            commit();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        atividades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().
                        beginTransaction().
                        replace(R.id.telaBotton, new atividadeRN()).addToBackStack(null).
                        commit();
            }
        });

        sobrenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tela_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment fragment = null;
        int id = item.getItemId();

        if (id ==R.id.nav_home){
            Intent intent = new Intent(this, TelaPrincipal.class);
            startActivity(intent);
        }else if (id == R.id.nav_estudo) {
            fragment = new ListagemEstudo();
        } else if (id == R.id.nav_video) {
            fragment = new ListagemVideo();
        } else if (id == R.id.nav_atividades) {

        } else if (id == R.id.nav_sobreNos) {

        }

        if (fragment != null){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.telaBotton,fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

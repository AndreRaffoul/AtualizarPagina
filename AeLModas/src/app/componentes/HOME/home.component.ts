import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CONTENTComponent } from '../HOME/content/content.component';
import { FOOTERComponent } from '../HOME/footer/footer.component';
import { MENUComponent } from '../HOME/menu/menu.component';


@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    MENUComponent,
    CONTENTComponent,
    FOOTERComponent,
    CommonModule, RouterModule
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HOMEComponent {

  imagemLogo: string = 'assets/imagens/logo.png';
  imagemBanner: string = 'banner.jpg';

}

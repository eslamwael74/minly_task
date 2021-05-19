import { Controller, Get, Param, Post, Res, UploadedFile, UseInterceptors } from '@nestjs/common';
import { AppService } from './app.service';
import { uploadInterceptor } from './file.interceptor';

@Controller("images")
export class AppController {
  constructor(private readonly appService: AppService) { }

  @Post('')
  @UseInterceptors(uploadInterceptor)
  async uploadedFile(@UploadedFile() file) {
    return this.appService.uploadedFile(file);
  }

  @Get(':imgpath')
  seeUploadedFile(@Param('imgpath') image, @Res() res) {
    return res.sendFile(image, { root: './files' });
  }

  @Get('')
  getAllImages() {
    return this.appService.getAllImages();
  }
}
